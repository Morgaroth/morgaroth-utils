package io.github.morgaroth.utils.spray.auth.mongoimpl

import com.typesafe.config.{ConfigObject, Config}

trait UserDBConfig {
  def userDbConfig: Config
}

trait SessionsDBConfig {
  def sessionsDbConfig: Config
}
