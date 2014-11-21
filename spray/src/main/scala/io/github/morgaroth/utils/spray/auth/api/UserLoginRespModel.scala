package io.github.morgaroth.utils.spray.auth.api

import spray.json.{RootJsonFormat, DefaultJsonProtocol}

case class UserLoginResp(token:String)

object UserLoginResp {
}

trait UserLoginRespModel extends DefaultJsonProtocol {
  implicit val UserLoginRespRootJsonFormat: RootJsonFormat[UserLoginResp] = jsonFormat1(UserLoginResp.apply)
}

object UserLoginRespModel extends UserLoginRespModel