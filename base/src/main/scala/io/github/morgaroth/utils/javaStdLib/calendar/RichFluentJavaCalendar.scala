package io.github.morgaroth.utils.javaStdLib.calendar

import java.util.Calendar

object RichFluentJavaCalendar {

  import scala.language.implicitConversions

  implicit def wrapCalendar(calendar: Calendar): RichFluentCalendar = new RichFluentCalendar(calendar)

  implicit def unwrapCalendar(wrapper: RichFluentCalendar): Calendar = wrapper.wrapped

  class RichFluentCalendar(val wrapped: Calendar) {

    def set(field: Int, value: Int) = {
      wrapped.set(field, value)
      this
    }

    def add(field: Int, value: Int) = {
      wrapped.add(field, value)
      this
    }

    def setTimeInMillis(timestamp: Long) = {
      wrapped.setTimeInMillis(timestamp)
      this
    }

    def fluent = this
  }

}
