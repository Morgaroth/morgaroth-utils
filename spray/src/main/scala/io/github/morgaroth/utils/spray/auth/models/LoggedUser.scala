package io.github.morgaroth.utils.spray.auth.models
import com.novus.salat.annotations._
import com.novus.salat.global._
import io.github.morgaroth.utils.mongodb.salat.SalatDAOWithCfg


case class LoggedUser(
                       @Key("_id") accessToken: String,
                       login: String
                       )

object LoggedUser extends SalatDAOWithCfg[LoggedUser, String]("kmx.micro.reports.logic.database.uri","loggedUsers") {
  def create(token: String, login: String) = save(LoggedUser(token, login))

  def validate(token: String, expectedLogin: String) = findOneById(token).exists(_.login.equals(expectedLogin))
}