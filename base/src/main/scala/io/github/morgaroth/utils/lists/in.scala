package io.github.morgaroth.utils.lists

object in {

  class inable[T](elem: T) {
    def in(list: List[T]) = list.contains(elem)
  }

  import scala.language.implicitConversions

  implicit def wrapIntoInable[T](elem: T): inable[T] = new inable(elem)
}
