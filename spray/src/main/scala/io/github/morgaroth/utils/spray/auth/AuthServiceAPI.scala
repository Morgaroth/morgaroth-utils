package io.github.morgaroth.utils.spray.auth

import io.github.morgaroth.utils.spray.auth.models.{UserLoginReq, UserLoginReqModel, UserLoginResp, UserLoginRespModel}
import io.github.morgaroth.utils.spray.cors.CORSDirectives
import spray.http.StatusCodes._
import spray.routing.AuthenticationFailedRejection.CredentialsRejected
import spray.routing._
import spray.routing.authentication.Authentication
import spray.routing.directives.{AuthMagnet, FutureDirectives}

import scala.concurrent.Future

/**
 * Responsibilities:
 * 1. Extracting authentication token from request context
 * 2. Providing API for logging user
 * 3. Providing API for validating logged user = enabled in application.conf
 */
trait AuthServiceAPI[UserType, SessionType <: UserSession]
  extends Directives
  with AuthServiceValidating[UserType] with AuthServiceSecureMethods[UserType]
  with CORSDirectives with AuthServiceHeader with FutureDirectives
  with UserLoginRespModel with UserLoginReqModel {

  this: AuthServiceAuthentication[UserType, SessionType] =>

  def checkSession(token: String): Future[Authentication[UserType]] = Future {
    checkValidSession(token) match {
      case Some(user) => Right(user)
      case None => Left(AuthenticationFailedRejection(CredentialsRejected, List.empty))
    }
  }

  override def validateDirective: Directive1[UserType] = headerValueByName(HeaderName).hflatMap(values =>
    authenticate(AuthMagnet.fromFutureAuth[UserType](checkSession(values.head)))
  )

  //@formatter:off
  val route =
    optionsForCors ~
    pathEndOrSingleSlash {
    get(complete("Hello from auth service")) ~
      post(respondWithCors {
        handleWith((userReq: UserLoginReq) =>
          loginUser(userReq.login, userReq.password) match {
            case Some(session) => Right(Created -> UserLoginResp(session.token))
            case None => Left(BadRequest)
          })
      }) ~
      delete(headerValueByName(HeaderName)(authToken =>
        complete {
          logoutUser(authToken)
          NoContent
        }
      ))
    } ~
    path("validate") {
      get(validateDirective(user =>
        user => complete(s"You have access as $user")
      ))
    }
  //@formatter:on
}