package io.github.morgaroth.utils.spray.auth

/**
 * Responsibility: log in/out user
 */
trait AuthServiceAuthentication // [UserType,SessionType] {
//  def loginUser(loginID: String, password: String)(implicit luDAO: AuthUserDAO[UserType,SessionType], tokenGenerator: AuthServiceTokenGenerator): Option[SessionType]
//  def logoutUser(token: String)
//}
//
//
//object AuthServiceAuthentication {
//
//}
//
//trait Logging[UserType,SessionType] extends AuthServiceAuthentication[UserType,SessionType] {
//
//  override def loginUser(loginID: String, password: String)
//                                     (implicit luDAO: AuthUserDAO[UserType,SessionType], tokenGenerator: AuthServiceTokenGenerator): Option[SessionType] = {
//    luDAO.findUserById(loginID) match {
//      case Some(user) =>
//        val token = tokenGenerator.generateToken
//        val session = luDAO.saveSession(token, user)
//        Some(session)
//      case None =>
//
//    }
//  }
//
//  override def logoutUser(token: String): Unit = ???
//
//
////  def performLogout(token: String)(implicit luDAO: LoggedUserDAO[UserType]): Boolean = LoggedUserImpl.findOneById(token) match {
////    case Some(user) =>
////      luDAO.logout(token)
////      true
////    case None =>
////      false
////  }
////}
////
////trait LoggingWithTokensCache[LoggedUserType] {
////  this: Logging[LoggedUserType] =>
////  var tokens: Map[String, String] = Map()
////
////  override def performLogin(user: UserLoginReq)(implicit luDAO: LoggedUserDAO[LoggedUserType], tokenGenerator: TokenGenerator): String = {
////    val token = performLogin(user)
////    tokens += token -> user.login
////    token
////  }
////  override def performLogout(token: String)(implicit luDAO: LoggedUserDAO[LoggedUserType]): Boolean = {
////    val result = performLogout(token)
////    tokens -= token
////    result
////  }
//}