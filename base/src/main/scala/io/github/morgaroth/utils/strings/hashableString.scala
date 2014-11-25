package io.github.morgaroth.utils.strings

import sun.misc.BASE64Encoder

object hashableString {

  def hasher = java.security.MessageDigest.getInstance("MD5")

  def encoder = new BASE64Encoder()

  class HashingRichString(string: String) {
    def toMD5 = encoder.encode(hasher.digest(string.getBytes))
  }

  import scala.language.implicitConversions

  implicit def wrapAsHashableString(string: String): HashingRichString = new HashingRichString(string)
}
