package io.github.morgaroth.utils

object returnablePrint {

  class PrintAndReturnWrapper[T](obj: T) {
    def printAndReturn = {
      println(obj)
      obj
    }
  }

  import scala.language.implicitConversions

  implicit def wrapIntoPrintableObject[T](obj: T): PrintAndReturnWrapper[T] = new PrintAndReturnWrapper(obj)
}
