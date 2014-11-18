package io.github.morgaroth.utils.javaStdLib.calendar

import java.text.SimpleDateFormat
import java.util.Locale

object DateFormatter {
  lazy val formatter = new SimpleDateFormat("HH:mm:ss:SSS dd/MM/yyyy", Locale.ENGLISH)

  //  lazy val formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.ENGLISH)
  def apply() = formatter
}
