package io.github.morgaroth.utils.spray.auth

import com.mongodb.casbah.commons.MongoDBObject
import io.github.morgaroth.utils.spray.auth.api.{UserLoginReq, UserLoginReqModel, UserLoginResp, UserLoginRespModel}
import io.github.morgaroth.utils.spray.auth.models.{LoggedUser, User}
import io.github.morgaroth.utils.spray.cors.CORSDirectives
import io.github.morgaroth.utils.strings.hashableString.wrapAsHashableString
import spray.http.ContentTypes.`application/json`
import spray.http._
import spray.httpx.SprayJsonSupport
import spray.routing.{AuthenticationFailedRejection, Directives}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random


object AuthService extends AuthServiceHeader

class AuthService(implicit exContext: ExecutionContext)
  extends Directives with CORSDirectives
  with SprayJsonSupport with UserLoginRespModel with UserLoginReqModel
  with AuthServiceHeader {

  if (User.findOneById("admin").isEmpty) {
    User.save(User("admin", "ala123".toMD5))
  }

  def validate(token: String): Future[Either[AuthenticationFailedRejection, String]] = Future {
    LoggedUser.findOneById(token) match {
      case Some(user) =>
        Right(user.login)
      case None =>
        Left(AuthenticationFailedRejection(AuthenticationFailedRejection.CredentialsRejected, List[HttpHeader]()))
    }
  }

  def validate(header: XSessionHeader): Future[Either[AuthenticationFailedRejection, String]] = validate(header.value)

  private var tokens: Map[String, String] = LoggedUser.find(MongoDBObject.empty).toList.groupBy(_.accessToken).mapValues(_.head.login)
  private val chars: List[Char] = "1234567890QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm".toList

  private def generateToken(not: Seq[String]): String = Random.shuffle(chars).take(50).mkString match {
    case equal if not.contains(equal) => generateToken(not)
    case some => some
  }

  private def performLogin(user: UserLoginReq) = {
    val token = generateToken(tokens.keys.toSeq)
    tokens += token -> user.login
    LoggedUser.create(token, user.login)
    token
  }

  private def performLogout(token: String): Boolean = LoggedUser.findOneById(token) match {
    case Some(user) =>
      tokens -= token
      LoggedUser.remove(user)
      true
    case None =>
      false
  }

  val route =
    optionsForCors ~
      pathEndOrSingleSlash {
        get(complete("Hello from auth service")) ~
          post(respondWithCors {
            handleWith((user: UserLoginReq) =>
              User.findOneById(user.login) match {
                case None => Left(HttpResponse(StatusCodes.NotFound))
                case Some(_) => User.validate(user.login, user.password) match {
                  case false => Left(HttpResponse(StatusCodes.BadRequest))
                  case true =>
                    val token = performLogin(user)
                    Right(HttpResponse(StatusCodes.Created, HttpEntity(`application/json`, UserLoginRespRootJsonFormat.write(UserLoginResp(token)).compactPrint)))
                }
              }
            )
          }) ~
          delete(headerValueByName(HeaderName)(authToken =>
            complete {
              performLogout(authToken)
              StatusCodes.NoContent
            }
          ))
      } ~
      path("validate") {
        get {
          headerValueByType[XSessionHeader]() {
            authHeader => authenticate(validate(authHeader)) {
              user => complete(s"You have access as $user")
            }
          }
        }
      }
}