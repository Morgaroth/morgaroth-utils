package io.github.morgaroth.utils.files

import java.io.File

trait PrintToFile {

  import scala.language.implicitConversions

  implicit def wrapToPrintableToFile(data: String) = new {
    def printToFile(file: File): Unit = internalPrintToFile(file)(_.write(data))
    def printToFile(path: String): Unit = printToFile(new File(path))
  }

  def internalPrintToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
    val p = new java.io.PrintWriter(f)
    try {
      if (!f.exists()) {
        f.createNewFile()
      }
      op(p)
    } finally {
      p.close()
    }
  }


}

object PrintToFile extends PrintToFile