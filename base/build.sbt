
name := s"${ThisProject.projectName}-base"

version := ThisProject.projectVersion

organization := ThisProject.organization

scalacOptions ++= Seq("-feature")

pomExtra := ThisProject.commonPomFile

publishTo := ThisProject.publishTo