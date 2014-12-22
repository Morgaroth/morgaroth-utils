package io.github.morgaroth.utils.spray.auth.mongoimpl

import com.typesafe.config.Config

trait UsersDBConfig {
  def usersDbConfig: Config
}

trait SessionsDBConfig {
  def sessionsDbConfig: Config
}
