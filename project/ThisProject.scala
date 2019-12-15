import sbt._
import Keys._

import scala.xml.NodeBuffer


object ThisProject {

  val akkaVersion = "2.5.3"

  object Deps {
    val Ficus = "com.iheart" %% "ficus" % "1.4.1" withSources()
    val TypesafeCfg = "com.typesafe" % "config" % "1.3.1" withSources()

  }

  case class PomData(repoUrl: String, developerUrl: String)

  def projectName = "morgaroth-utils"

  def projectVersion = "2.0.0-SNAPSHOT"

  val organization: String = "io.github.morgaroth"

  def commonPomFile: NodeBuffer = generatePomFile(
    PomData("https://github.com/Morgaroth/morgaroth-utils", "git@github.com:Morgaroth/morgaroth-utils.git")
  )

  def publishTo(): Some[MavenRepository] = {
    val nexus = "https://oss.sonatype.org/"
    if (projectVersion.endsWith("SNAPSHOT"))
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
  }

  def generatePomFile(pomData: PomData): NodeBuffer = <url>
    {pomData.repoUrl}
  </url>
    <licenses>
      <license>
        <name>BSD-style</name>
        <url>http://www.opensource.org/licenses/bsd-license.php</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>
        {pomData.developerUrl}
      </url>
      <connection>scm:git:
        {pomData.developerUrl}
      </connection>
    </scm>
    <developers>
      <developer>
        <id>morgaroth</id>
        <name>Mateusz Jaje</name>
      </developer>
    </developers>
}