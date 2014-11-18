import sbt._
import Keys._


object ThisProject {

  case class PomData(repoUrl:String,developerUrl:String)

  def projectName = "morgaroth-utils"
  def projectVersion = "1.2.4-SNAPSHOT"

  def generatePomFile(pomData: PomData) = <url>{pomData.repoUrl}</url>
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