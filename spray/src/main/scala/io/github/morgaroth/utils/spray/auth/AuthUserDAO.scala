package io.github.morgaroth.utils.spray.auth

/**
 * Trait that provide access to users list
 */
trait UserDAO[UserType] {
  def findUserById(id: String): Option[UserType]
  def checkCorrectPassword(id: String, password: String): Boolean
}

/**
 * Trait that provide access to user sessions
 */
trait SessionDAO[UserType, SessionType <: UserSession] {
  def findSession(token: String): Option[SessionType]
  def saveSession(token: String, user: UserType): SessionType
  def deleteSession(token: String)
}