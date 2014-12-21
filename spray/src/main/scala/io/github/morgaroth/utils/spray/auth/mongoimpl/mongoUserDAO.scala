package io.github.morgaroth.utils.spray.auth.mongoimpl

import com.mongodb.WriteConcern
import com.novus.salat.annotations.Key
import com.novus.salat.dao.SalatDAO
import io.github.morgaroth.utils.mongodb.salat.SalatDAOWithCfg
import io.github.morgaroth.utils.spray.auth.UserDAO
import net.ceedubs.ficus.Ficus._
import com.novus.salat.global.ctx


trait MongoUserDAO[UserType] extends UserDAO[UserType] {
  this: SalatDAO[UserType, String] =>
  override def findUserById(id: String): Option[UserType] = findOneById(id)
}

case class DefaultMongoUser(
                             @Key("_id") email: String,
                             password: String
                             )

trait DefaultMongoUserDAO extends UserDAO[DefaultMongoUser] {
  this: UserDBConfig =>

  private val dbUri: String = userDbConfig.as[Option[String]]("uri").getOrElse {
    throw new IllegalArgumentException(s"You must provide database uri as `uri` field in provided by userDbConfig config!")
  }
  private val collectionNameCfg: String = userDbConfig.as[Option[String]]("name").getOrElse("users")

  lazy val dao = new SalatDAOWithCfg[DefaultMongoUser, String](dbUri, collectionNameCfg) with MongoUserDAO[DefaultMongoUser] {
    override def checkCorrectPassword(id: String, password: String) = findUserById(id) match {
      case None => false
      case Some(user) => user.password == password
    }
  }

  override def findUserById(id: String): Option[DefaultMongoUser] = dao.findOneById(id)
  override def checkCorrectPassword(id: String, password: String): Unit = dao.checkCorrectPassword(id, password)
}
