package io.github.morgaroth.utils.spray.auth.api

import spray.json.{RootJsonFormat, DefaultJsonProtocol}

case class UserLoginReq(
                         login: String,
                         password: String
                         )

object UserLoginReq {
}

trait UserLoginReqModel extends DefaultJsonProtocol {
  implicit val UserLoginReqRootJsonFormat: RootJsonFormat[UserLoginReq] = jsonFormat2(UserLoginReq.apply)
}

object UserLoginReqModel extends UserLoginReqModel