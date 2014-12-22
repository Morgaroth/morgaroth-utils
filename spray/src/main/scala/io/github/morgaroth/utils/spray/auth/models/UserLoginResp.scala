package io.github.morgaroth.utils.spray.auth.models

import spray.json.{RootJsonFormat, DefaultJsonProtocol}

case class UserLoginResp(token: String)

trait UserLoginRespModel extends DefaultJsonProtocol {
  implicit val UserLoginRespRootJsonFormat: RootJsonFormat[UserLoginResp] = jsonFormat1(UserLoginResp.apply)
}
