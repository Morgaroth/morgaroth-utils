import scala.util.Random

case class Obj(s: String)

trait hasObj {
  def getObj: Obj
}
trait hasObjImpl extends hasObj {
  override def getObj: Obj = Obj("from hasObj trait")
}
trait hasObjImpl2 extends hasObj {
  override def getObj: Obj = Obj("from hasObj trait2")
}

trait objPrinter {
  this: hasObj =>
  def go() = getObj
}

val t1 = new hasObjImpl with objPrinter
t1.go()

val t2 = new hasObjImpl with objPrinter with hasObjImpl2
t2.go()


