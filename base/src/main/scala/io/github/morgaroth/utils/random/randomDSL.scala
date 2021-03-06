package io.github.morgaroth.utils.random

import scala.util.Random

trait randomDSL {

  import language.implicitConversions

  implicit def wrapToRandomableInts(elem: (Int, Int)): Object {def random(): Int} = new {
    def random(): Int = Random.nextInt(elem._2 - elem._1) + elem._1
  }

  implicit def wrapToRandomableLongs(elem: (Long, Long)): Object {def random(): Long} = new {
    def random(): Long = Random.nextLong() % elem._2 + elem._1
  }

  implicit def wrapIntoRandomable(elem: Int): Object {def ~(end: Int): (Int, Int)} = new {
    def ~(end: Int) = elem -> end
  }

  implicit def wrapIntoRandomable(elem: Long): Object {def ~(end: Long): (Long, Long)} = new {
    def ~(end: Long) = elem -> end
  }

}

object randomDSL extends randomDSL