package io.github.morgaroth.utils.javaStdLib.calendar

case class Timer(startTime: Long) {

  import io.github.morgaroth.utils.javaStdLib.calendar.Timer._

  def tack(description: String = "it") = {
    val current = currentTime
    val elapsed = currentTime - startTime
    println(s"$description takes ${elapsed}ms = ${elapsed / 1000}s")
    elapsed
  }
}

object Timer {

  def currentTime = System.currentTimeMillis()

  def tick = Timer(currentTime)

}
