package io.github.morgaroth.utils.spray.auth

import java.util.UUID

/**
 * Responsibility: Generating tokens
 */
trait AuthServiceTokenGenerator {
  def generateToken: String
}

trait DefaultTokenGenerator extends AuthServiceTokenGenerator {
  private def uuid: String = StringBuilder.newBuilder
    .append(UUID.randomUUID())
    .append(UUID.randomUUID())
    .append(UUID.randomUUID())
    .append(UUID.randomUUID())
    .result().replaceAll("\\-", "")

  override def generateToken: String = uuid
}


