package io.github.morgaroth.utils.spray.auth

import scala.util.Random

/**
 * Responsibility: Generating tokens
 */
trait AuthServiceTokenGenerator {
  def generateToken: String
}

object AuthServiceTokenGenerator {
  implicit def defaultTokenGenerator: TokenGenerator = new TokenGenerator
}


class TokenGenerator extends AuthServiceTokenGenerator {
  override def generateToken: String = Random.alphanumeric.take(50).mkString
}


