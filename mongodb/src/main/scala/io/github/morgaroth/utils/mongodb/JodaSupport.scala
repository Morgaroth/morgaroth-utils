package io.github.morgaroth.utils.mongodb

import com.mongodb.casbah.commons.conversions.scala.{RegisterJodaLocalDateTimeConversionHelpers, RegisterJodaTimeConversionHelpers}
import salat.conversions.RegisterJodaTimeZoneConversionHelpers
import salat.dao.SalatDAO


trait JodaSupport {
  this: SalatDAO[_, _] =>
  RegisterJodaLocalDateTimeConversionHelpers()
  RegisterJodaTimeConversionHelpers()
  RegisterJodaTimeZoneConversionHelpers()
}
