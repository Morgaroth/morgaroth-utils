package io.github.morgaroth.utils.spray.auth.mongoimpl

import com.typesafe.config.Config
import io.github.morgaroth.utils.spray.auth.{UserSession, SessionDAO, UserDAO}


trait AuthServiceAuthUser[UserType, SessionType <: UserSession] {
  def userDAO: UserDAO[UserType]
  def sessionDAO: SessionDAO[UserType, SessionType]
}

case class DefaultMongoAuth(userCfg: Config, sessionCfg: Config) extends AuthServiceAuthUser[DefaultMongoUser, DefaultMongoSession] {
  val userDAO = new UserDBConfig with DefaultMongoUserDAO {
    override def userDbConfig: Config = userCfg
  }

  val sessionDAO = new SessionsDBConfig with DefaultMongoSessionDAO[DefaultMongoUser] {
    override def sessionsDbConfig: Config = sessionCfg
  }
}
