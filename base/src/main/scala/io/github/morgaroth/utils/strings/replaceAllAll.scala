package io.github.morgaroth.utils.strings

object replaceAllAll {

  class RichString(str: String) {

    def replaceAllAll(pairRegexReplacement: Seq[(String, String)]): String =
      pairRegexReplacement match {
        case Nil => str
        case head :: tail => wrapToReplacingAllAllString(str.replaceAll(head._1, head._2)).replaceAllAll(tail)
      }

    def replaceAllAll(pairRegexReplacement: (String, String)*)(implicit s: DummyImplicit): String = replaceAllAll(pairRegexReplacement)
  }

  import scala.language.implicitConversions

  implicit def wrapToReplacingAllAllString(str: String) = new RichString(str)
}
