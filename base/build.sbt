import ThisProject.PomData

name := s"${ThisProject.projectName}-base"

version := ThisProject.projectVersion

scalacOptions ++= Seq("-feature")

pomExtra := ThisProject.generatePomFile(
  PomData("https://github.com/Morgaroth/morgaroth-utils","git@github.com:Morgaroth/morgaroth-utils.git")
)