package io.github.morgaroth.utils.spray.auth.mongoimpl

import com.novus.salat.annotations.Key
import io.github.morgaroth.utils.mongodb.salat.SalatDAOWithCfg
import io.github.morgaroth.utils.spray.auth.{UserSession, SessionDAO}
import net.ceedubs.ficus.Ficus._
import com.novus.salat.global.ctx


case class DefaultMongoSession(
                                @Key("_id") token: String,
                                userId: String
                                ) extends UserSession

trait DefaultMongoSessionDAO[UserType <: IdAble] extends SessionDAO[UserType, DefaultMongoSession] {
  this: SessionsDBConfig =>

  private val dbUri: String = sessionsDbConfig.as[Option[String]]("uri").getOrElse {
    throw new IllegalArgumentException(s"You must provide database uri as `uri` field in provided by sessionsDbConfig config!")
  }
  private val collectionNameCfg: String = sessionsDbConfig.as[Option[String]]("name").getOrElse("loggedUsers")

  lazy val dao = new SalatDAOWithCfg[DefaultMongoSession, String](dbUri, collectionNameCfg) with MongoSessionsDAO[UserType, DefaultMongoSession] {
    override def saveSession(token: String, user: UserType): DefaultMongoSession = DefaultMongoSession(token, user.getId)
  }

  override def findSession(token: String): Option[DefaultMongoSession] = dao.findOneById(token)
  override def saveSession(token: String, user: UserType): DefaultMongoSession = dao.saveSession(token, user)
  override def deleteSession(token: String): Unit = dao.deleteSession(token)
}
