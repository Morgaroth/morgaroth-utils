name := s"${ThisProject.projectName}-akka"

version := ThisProject.projectVersion

scalacOptions ++= Seq("-feature")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % ThisProject.akkaVersion
)
