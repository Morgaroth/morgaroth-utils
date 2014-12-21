package io.github.morgaroth.utils.spray.auth_old

import scala.util.Random

trait TokenGenerator {
  def generateToken: String
}

object TokenGeneratorImpl extends TokenGenerator {
  override def generateToken: String = Random.alphanumeric.take(50).mkString
}
