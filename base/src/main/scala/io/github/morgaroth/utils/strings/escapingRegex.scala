package io.github.morgaroth.utils.strings

object escapingRegex {

  class RichString(str: String) {
    def escapeMe =
      str.replaceAll("\\-", """\\-""").
        replaceAll("\\?", """\\?""").
        replaceAll("\\[", """\[""").
        replaceAll("\\]", """\]""").
        replaceAll("\\.", """\.""")
  }

  import scala.language.implicitConversions

  implicit def wrapToRich(str: String): RichString = new RichString(str)
}