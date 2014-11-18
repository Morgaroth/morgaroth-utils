package io.github.morgaroth.utils.random

import scala.util.Random

trait randoms {
  val randGenerator = Random

  def randomInt = randGenerator.nextInt()

  def randInt = randomInt

  def randomInt(max: Int) = randGenerator.nextInt(max)

  def randInt(max: Int) = randomInt(max)

  def randomDouble = randGenerator.nextDouble()

  def randDouble = randomDouble
}

object randoms extends randoms {

}
