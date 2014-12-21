package io.github.morgaroth.utils.spray.auth_old

import com.mongodb.casbah.commons.MongoDBObject
import io.github.morgaroth.utils.spray.auth.{AuthServiceHeader, XSessionHeader}
import io.github.morgaroth.utils.spray.auth_old.api.{UserLoginReq, UserLoginReqModel, UserLoginResp, UserLoginRespModel}
import io.github.morgaroth.utils.spray.auth_old.models.LoggedUserImpl
import io.github.morgaroth.utils.spray.cors.CORSDirectives
import io.github.morgaroth.utils.strings.hashableString.wrapAsHashableString
import spray.http._
import spray.httpx.SprayJsonSupport
import spray.routing.{AuthenticationFailedRejection, Directives}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

trait AuthTypes {
  type LoggedUser = LoggedUserImpl
}


trait UserDAO {
  this: AuthTypes =>
}

trait LoggedUserDAO[T] {
  def findLoggedByToken(authToken: String): Option[T]
  def login(token: String, userID: String)
  def logout(token: String)
}

trait Validating[LoggedUserType] {
  def validate(token: String)(implicit luDAO: LoggedUserDAO[LoggedUserType]): Future[Either[AuthenticationFailedRejection, LoggedUserType]] = Future {
    luDAO.findLoggedByToken(token) match {
      case Some(user) =>
        Right(user)
      case None =>
        Left(AuthenticationFailedRejection(AuthenticationFailedRejection.CredentialsRejected, List[HttpHeader]()))
    }
  }
  def validate(header: XSessionHeader): Future[Either[AuthenticationFailedRejection, LoggedUserType]] = validate(header.value)
}

trait Logging[LoggedUserType] {
  def performLogin(user: UserLoginReq)(implicit luDAO: LoggedUserDAO[LoggedUserType], tokenGenerator: TokenGenerator): String = {
    val token = tokenGenerator.generateToken
    luDAO.login(token, user.login)
    token
  }

  def performLogout(token: String)(implicit luDAO: LoggedUserDAO[LoggedUserType]): Boolean = LoggedUserImpl.findOneById(token) match {
    case Some(user) =>
      luDAO.logout(token)
      true
    case None =>
      false
  }
}

trait LoggingWithTokensCache[LoggedUserType] {
  this: Logging[LoggedUserType] =>
  var tokens: Map[String, String] = Map()

  override def performLogin(user: UserLoginReq)(implicit luDAO: LoggedUserDAO[LoggedUserType], tokenGenerator: TokenGenerator): String = {
    val token = performLogin(user)
    tokens += token -> user.login
    token
  }
  override def performLogout(token: String)(implicit luDAO: LoggedUserDAO[LoggedUserType]): Boolean = {
    val result = performLogout(token)
    tokens -= token
    result
  }
}

class AuthService(implicit exContext: ExecutionContext)
  extends Directives with CORSDirectives
  with SprayJsonSupport with UserLoginRespModel with UserLoginReqModel
  with AuthServiceHeader {

  if (User.findOneById("admin").isEmpty) {
    User.save(User("admin", "ala123".toMD5))
  }

  def validate(token: String): Future[Either[AuthenticationFailedRejection, String]] = Future {
    LoggedUserImpl.findOneById(token) match {
      case Some(user) =>
        Right(user.login)
      case None =>
        Left(AuthenticationFailedRejection(AuthenticationFailedRejection.CredentialsRejected, List[HttpHeader]()))
    }
  }

  def validate(header: XSessionHeader): Future[Either[AuthenticationFailedRejection, String]] = validate(header.value)

  private var tokens: Map[String, String] = LoggedUserImpl.find(MongoDBObject.empty).toList.groupBy(_.accessToken).mapValues(_.head.login)
  private val chars: List[Char] = "1234567890QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm".toList

  private def generateToken(not: Seq[String]): String = Random.shuffle(chars).take(50).mkString match {
    case equal if not.contains(equal) => generateToken(not)
    case some => some
  }

  private def performLogin(user: UserLoginReq) = {
    val token = generateToken(tokens.keys.toSeq)
    tokens += token -> user.login
    LoggedUserImpl.create(token, user.login)
    token
  }

  private def performLogout(token: String): Boolean = LoggedUserImpl.findOneById(token) match {
    case Some(user) =>
      tokens -= token
      LoggedUserImpl.remove(user)
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
                    Right(HttpResponse(StatusCodes.Created, HttpEntity(MediaTypes.`application/json`, UserLoginRespRootJsonFormat.write(UserLoginResp(token)).compactPrint)))
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