

trait Bar {
  def foo = "foo from trait"
}


class BarImpl extends Bar {
  override def foo: String = "foo from implementation"
}

object Bar {
  implicit val SomeDefault = new BarImpl
}

object Test {
  def SomeImplTester(implicit s: Bar) = s.foo
}
import Bar._

/*
  It works!
 */
Test.SomeImplTester