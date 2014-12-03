name := s"${ThisProject.projectName}-akka"

version := ThisProject.projectVersion

organization := ThisProject.organization

scalacOptions ++= Seq("-feature")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % ThisProject.akkaVersion
)

pomExtra := ThisProject.commonPomFile

publishTo := ThisProject.publishTo