package io.github.morgaroth.utils.spray.cors

import spray.http.AllOrigins
import spray.http.HttpHeaders._
import spray.http.HttpMethods._
import spray.routing.{Directive0, Directives}

trait CORSDirectives {
  this: Directives =>

  val corsHeaders = List(
    `Access-Control-Allow-Origin`(AllOrigins),
    `Access-Control-Allow-Credentials`(allow = true),
    `Access-Control-Allow-Methods`(POST, GET, OPTIONS, DELETE),
    `Access-Control-Allow-Headers`("Content-Type", "Accept", "X-Session-Header")
  )
  val respondWithCors: Directive0 = respondWithHeaders(corsHeaders)
  val optionsForCors = options(respondWithCors(complete("OK")))
}