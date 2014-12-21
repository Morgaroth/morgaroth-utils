package io.github.morgaroth.utils.mongodb.test

import com.typesafe.config.Config

trait DBConfig {
  def dbConfig: Config
}
