package pl.morgaroth

package object utils {
  def printning[T](any: T, prefix: String = "", postfix: String = "")
                  (stringifier: (T => String) = (a: T) => a.toString) = {
    println(s"$prefix${stringifier(any)}$postfix")
    any
  }
}
