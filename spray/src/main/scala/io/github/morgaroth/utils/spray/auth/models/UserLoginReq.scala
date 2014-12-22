package io.github.morgaroth.utils.spray.auth.models

import spray.json.{RootJsonFormat, DefaultJsonProtocol}

case class UserLoginReq(
                         login: String,
                         password: String
                         )

trait UserLoginReqModel extends DefaultJsonProtocol {
  implicit val UserLoginReqRootJsonFormat: RootJsonFormat[UserLoginReq] = jsonFormat2(UserLoginReq.apply)
}
