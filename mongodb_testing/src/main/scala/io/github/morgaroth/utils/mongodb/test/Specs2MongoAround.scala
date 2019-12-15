package io.github.morgaroth.utils.mongodb.test

import com.mongodb.casbah.{MongoClient, MongoClientURI}
import net.ceedubs.ficus.Ficus._
import org.specs2.mutable.Specification
import org.specs2.specification.BeforeAfterEach

/**
  * This trait remove test database before and after each example
  * (block in { tests... })
  * it require to be run in sequential mode and
  */
trait Specs2MongoAround extends BeforeAfterEach {
  this: Specification with DBConfig =>
  sequential

  val uri = MongoClientURI(dbConfig.as[String]("uri"))


  private def dropDatabaseFromUri() = {
    val client = MongoClient(uri)
    uri.database.foreach(client.dropDatabase)
  }

  override def after: Any = dropDatabaseFromUri()

  override protected def before: Any = dropDatabaseFromUri()
}
