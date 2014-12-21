package io.github.morgaroth.utils.spray.auth

/**
 * Responsibility: returning result of validating with user object, if proper
 */
trait AuthServiceValidating[SessionType <: UserSession] {
  this: SessionDAO[_, SessionType] =>
  def validate(token: String): Option[SessionType]
}