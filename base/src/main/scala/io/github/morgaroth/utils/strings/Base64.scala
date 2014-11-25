package io.github.morgaroth.utils.strings

import sun.misc.{BASE64Decoder, BASE64Encoder}

trait Base64Encoding {

  class ToBase64EncodableByteArray(data: Array[Byte]) {
    def toBase64: String = new BASE64Encoder().encode(data)
  }

  import scala.language.implicitConversions

  implicit def wrapIntoEncodable(string: String): Base64Encoding.this.type#ToBase64EncodableByteArray = new ToBase64EncodableByteArray(string.getBytes)

  implicit def wrapIntoEncodable(byteArray: Array[Byte]): Base64Encoding.this.type#ToBase64EncodableByteArray = new ToBase64EncodableByteArray(byteArray)
}

trait Base64Decoding {

  class FromBase64DecodableString(string: String) {
    def fromBase64: Array[Byte] = new BASE64Decoder().decodeBuffer(string)
  }

  import scala.language.implicitConversions

  implicit def wrapIntoDecodableString(string: String): Base64Decoding.this.type#FromBase64DecodableString = new FromBase64DecodableString(string)
}

trait Base64Operations extends Base64Encoding with Base64Decoding


