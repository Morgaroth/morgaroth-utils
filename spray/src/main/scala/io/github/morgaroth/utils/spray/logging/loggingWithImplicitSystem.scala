package io.github.morgaroth.utils.spray.logging

import akka.actor.ActorSystem
import akka.event.LoggingAdapter

trait HasImplicitActorSystem {
  implicit def actorSystem: ActorSystem
}

trait HasLogger {
  this: HasImplicitActorSystem =>
  lazy val log: LoggingAdapter = akka.event.Logging(actorSystem, this.getClass.getCanonicalName)
}

trait HasImplicitLogger extends HasLogger {
  this: HasImplicitActorSystem =>
  implicit lazy val implicitLog: LoggingAdapter = log
  implicit lazy val implicitLogOpt: Some[LoggingAdapter] = Some(log)

}
