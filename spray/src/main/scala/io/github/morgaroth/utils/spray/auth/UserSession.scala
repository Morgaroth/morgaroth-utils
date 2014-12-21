package io.github.morgaroth.utils.spray.auth

trait UserSession {
  def userId: String
  def token: String
}

trait UserSessionCompanion {
  def apply[T <: UserSession](userID: String, token: String): T
}
