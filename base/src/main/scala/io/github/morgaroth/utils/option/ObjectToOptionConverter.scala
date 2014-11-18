package io.github.morgaroth.utils.option

object ObjectToOptionConverter {

  import scala.language.implicitConversions

  implicit def convertToOption[A](obj: A) = obj match {
    case null => None
    case e => Some(e)
  }
}
