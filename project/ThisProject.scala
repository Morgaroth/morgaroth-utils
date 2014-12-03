import sbt._
import Keys._

import scala.xml.NodeBuffer


object ThisProject {

  val akkaVersion = "2.3.6"

  case class PomData(repoUrl:String,developerUrl:String)

  def projectName = "morgaroth-utils"
  def projectVersion = "1.2.5"

  val organization: String = "io.github.morgaroth"

  def commonPomFile: NodeBuffer = generatePomFile(
    PomData("https://github.com/Morgaroth/morgaroth-utils", "git@github.com:Morgaroth/morgaroth-utils.git")
  )

  def publishTo(): Some[MavenRepository] = {
    val nexus = "https://oss.sonatype.org/"
    if (projectVersion.endsWith("SNAPSHOT"))
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  }

  def generatePomFile(pomData: PomData): NodeBuffer = <url>{pomData.repoUrl}</url>
    <licenses>
      <license>
        <name>BSD-style</name>
        <url>http://www.opensource.org/licenses/bsd-license.php</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>{pomData.developerUrl}</url>
      <connection>scm:git:{pomData.developerUrl}</connection>
    </scm>
    <developers>
      <developer>
        <id>morgaroth</id>
        <name>Mateusz Jaje</name>
      </developer>
    </developers>
}