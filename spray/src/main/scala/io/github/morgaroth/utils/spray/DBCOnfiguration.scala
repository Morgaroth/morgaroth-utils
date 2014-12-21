package io.github.morgaroth.utils.spray

trait DBCOnfiguration {
  def configPath: String = ???
  def fullPath(postFix: String) = s"$configPath.$postFix"
}

object DBCOnfiguration {
  implicit val defaultDBConfig = new DBCOnfiguration {
    override def configPath = "app.database"
  }
}