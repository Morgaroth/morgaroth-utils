package io.github.morgaroth.utils.spray.auth.mongoimpl

import com.mongodb.casbah.commons.MongoDBObject
import com.novus.salat.dao.SalatDAO
import io.github.morgaroth.utils.spray.auth.{AuthServiceTokenGenerator, UserSession, SessionDAO, UserDAO}

trait MongoUserDAO[UserType <: AnyRef] extends UserDAO[UserType] {
  this: SalatDAO[UserType, String] =>
  override def findUserById(id: String): Option[UserType] = findOneById(id)
}

trait MongoSessionsDAO[UserType <: AnyRef, SessionType <: UserSession]
  extends SessionDAO[UserType, SessionType] {

  this: SalatDAO[SessionType, String] =>
  override def deleteSession(token: String): Unit = remove(MongoDBObject("_id" -> token))
  override def findSession(token: String): Option[SessionType] = findOneById(token)
}