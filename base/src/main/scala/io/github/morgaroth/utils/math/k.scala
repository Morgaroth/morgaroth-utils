package io.github.morgaroth.utils.math

object k {
  private class Kable(val multiplier: Int)
  private val k_ = new Kable(1)
  private val kk_ = new Kable(2)
  private val kkk_ = new Kable(3)

  import scala.language.implicitConversions

  implicit def wrapIntoKAble(double: Double) = new {
    def k = this.~(k_)
    def kk = this.~(kk_)

    private def ~(multi: Kable) = {
      var ret = double
      (1 to multi.multiplier).foreach {
        i => ret *= 1000
      }
      ret.toInt
    }
  }
}
