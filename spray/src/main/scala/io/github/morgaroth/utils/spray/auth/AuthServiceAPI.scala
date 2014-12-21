package io.github.morgaroth.utils.spray.auth

import io.github.morgaroth.utils.spray.auth.XSessionHeader
import io.github.morgaroth.utils.spray.auth_old.api.{UserLoginRespModel, UserLoginResp, UserLoginReq}
import io.github.morgaroth.utils.spray.cors.CORSDirectives
import spray.http.{MediaTypes, HttpEntity, StatusCodes, HttpResponse}
import spray.routing.Directives
import spray.routing.directives.RouteDirectives

/**
 * Responsibilities:
 * 1. Extracting authentication token from request context
 * 2. Providing API for logging user
 * 3. Providing API for validating logged user = enabled in application.conf
 */
class AuthServiceAPI

//[UserType, SessionType <: UserSession](
//                                                            authUserDAO: AuthUserDAO[UserType, SessionType],
//                                                            tokenGenerator: AuthServiceTokenGenerator
//                                                            )
//  extends RouteDirectives with Directives with CORSDirectives with UserLoginRespModel with AuthServiceHeader {
//  //@formatter:off
//  val route =
//    optionsForCors ~
//      pathEndOrSingleSlash {
//        get(complete("Hello from auth service")) ~
//          post(respondWithCors {
//            handleWith((userReq: UserLoginReq) =>
//              authUserDAO.findUserById(userReq.login) match {
//                case None => Left(HttpResponse(StatusCodes.NotFound))
//                case Some(user) =>
//                  authUserDAO.checkCorrectPassword(userReq.login, userReq.password) match {
//                    case false => Left(HttpResponse(StatusCodes.BadRequest))
//                    case true =>
//                      val session = authUserDAO.saveSession(tokenGenerator.generateToken, user)
//                      Right(HttpResponse(StatusCodes.Created, HttpEntity(MediaTypes.`application/json`, UserLoginRespRootJsonFormat.write(UserLoginResp(session.token)).compactPrint)))
//                  }
//              }
//            )
//          }) ~
//          delete(headerValueByName(HeaderName)(authToken =>
//            complete {
//              authUserDAO.deleteSession(authToken)
//              StatusCodes.NoContent
//            }
//          ))
//      } ~
//      path("validate") {
//        get {
//          headerValueByType[XSessionHeader]() {
//            authHeader => authenticate(validate(authHeader)) {
//              user => complete(s"You have access as $user")
//            }
//          }
//        }
//      }
//  //@formatter:on
//}