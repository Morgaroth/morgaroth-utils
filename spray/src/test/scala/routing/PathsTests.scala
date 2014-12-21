package routing

import org.scalatest.{WordSpec, Matchers, WordSpecLike}
import spray.http.StatusCodes
import spray.http.StatusCodes.OK
import spray.routing.Directives
import spray.testkit.ScalatestRouteTest

class PathsTests extends WordSpec with ScalatestRouteTest with Directives with Matchers {

  val sameSlashPath = "/"

  val pathSame = path("") {
    pathEndOrSingleSlash {
      get(complete("OK"))
    }
  }

  val pathSamePrefix = pathPrefix("") {
    pathEndOrSingleSlash {
      get(complete("OK"))
    }
  }

  "path /" should {
    "respond when used 'path' directive " in {
      Get("/") ~> pathSame ~> check {
        status shouldBe equal(OK)
      }
    }
    "respond when used 'pathPrefix' directive " in {
      Get("/") ~> pathSamePrefix ~> check {
        status shouldBe equal(OK)
      }
    }
  }
}
