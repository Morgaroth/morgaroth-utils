package io.github.morgaroth.utils.lists

object CartesianProduct {

  class ListWrapper[T](list: Iterable[T]) {
    def cartesianProductWith[U](another: Iterable[U]) = {
      for {
        i <- list
        j <- another
      } yield (i, j)
    }
  }

  import scala.language.implicitConversions

  implicit def wrapToListWithCartesianProduct[a](list: Iterable[a]): ListWrapper[a] = new ListWrapper(list)
}
