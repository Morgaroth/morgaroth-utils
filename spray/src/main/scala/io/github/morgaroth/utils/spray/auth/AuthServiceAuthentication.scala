package io.github.morgaroth.utils.spray.auth

import scala.annotation.tailrec

/**
 * Responsibility: log in/out user
 */
trait AuthServiceAuthentication[UserType, SessionType <: UserSession] {
  this: UserDAO[UserType] with SessionDAO[UserType, SessionType] with AuthServiceTokenGenerator =>

  @tailrec
  private def findUnUsedToken: String = {
    val token = generateToken
    findSession(token) match {
      case Some(_) => findUnUsedToken
      case _ => token
    }
  }

  def loginUser(loginID: String, password: String): Option[SessionType] =
    checkCorrectPassword(loginID, password) match {
      case Some(user) =>
        Some(createSession(findUnUsedToken, user))
      case _ => None
    }

  def logoutUser(token: String): Unit = deleteSession(token)

  def checkValidSession(token: String): Option[UserType] = findSession(token) match {
    case correct@Some(session) => findUserById(session.userId)
    case _ => None
  }
}