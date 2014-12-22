package io.github.morgaroth.utils.spray.auth.mongoimpl

import com.novus.salat.annotations._
import io.github.morgaroth.utils.mongodb.salat.SalatDAOWithCfg
import io.github.morgaroth.utils.spray.auth.UserDAO
import net.ceedubs.ficus.Ficus._
import com.novus.salat.global.ctx

trait IdAble extends AnyRef {
  def getId: String
}


case class DefaultMongoUser(
                             @Key("_id") email: String,
                             password: String
                             ) extends IdAble {
  override def getId: String = email
}

trait DefaultMongoUserDAO extends UserDAO[DefaultMongoUser] {
  this: UsersDBConfig =>

  private val dbUri: String = usersDbConfig.as[Option[String]]("uri").getOrElse {
    throw new IllegalArgumentException(s"You must provide database uri as `uri` field in provided by userDbConfig config!")
  }
  private val collectionNameCfg: String = usersDbConfig.as[Option[String]]("name").getOrElse("users")

  lazy val dao = new SalatDAOWithCfg[DefaultMongoUser, String](dbUri, collectionNameCfg) with MongoUserDAO[DefaultMongoUser] {
    override def checkCorrectPassword(id: String, password: String) = findUserById(id) match {
      case correct@Some(user) if user.password == password => correct
      case None => None
    }
  }

  override def findUserById(id: String): Option[DefaultMongoUser] = dao.findOneById(id)
  override def checkCorrectPassword(id: String, password: String) = dao.checkCorrectPassword(id, password)
}