package io.github.morgaroth.utils.spray.auth

import spray.http.{Rendering, HttpHeader}

trait AuthServiceHeader {
  val HeaderName = "X-Session-Header"
}

case class XSessionHeader(value: String) extends HttpHeader {

  import XSessionHeader._

  override def name: String = HeaderName

  override def lowercaseName: String = HeaderName.toLowerCase

  override def render[R <: Rendering](r: R): r.type = r ~~ name ~~ ':' ~~ ' ' ~~ value
}

object XSessionHeader extends AuthServiceHeader