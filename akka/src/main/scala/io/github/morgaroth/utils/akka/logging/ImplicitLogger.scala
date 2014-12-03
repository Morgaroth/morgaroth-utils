package io.github.morgaroth.utils.akka.logging

import akka.actor.ActorLogging
import akka.event.LoggingAdapter

trait ImplicitLogger {
  this: ActorLogging =>
  implicit val la: LoggingAdapter = log
  implicit val laOpt: Option[LoggingAdapter] = Some(la)
}

