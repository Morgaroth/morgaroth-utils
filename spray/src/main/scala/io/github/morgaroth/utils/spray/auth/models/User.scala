package io.github.morgaroth.utils.spray.auth.models

import com.novus.salat.annotations._
import com.novus.salat.global._
import io.github.morgaroth.utils.mongodb.salat.SalatDAOWithCfg
import io.github.morgaroth.utils.strings.hashableString.wrapAsHashableString

case class User(
                 @Key("_id") login: String,
                 hashedPassword: String
                 )

object User extends SalatDAOWithCfg[User, String]("kmx.micro.reports.logic.database.uri", "users") {

  def create(login: String, password: String) = User(login, password.toMD5)

  def validate(login: String, password: String) = findOneById(login).exists(_.hashedPassword.equals(password.toMD5))
}