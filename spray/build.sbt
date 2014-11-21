name := s"${ThisProject.projectName}-spray"

version := ThisProject.projectVersion

scalacOptions ++= Seq("-feature")

val sprayV = "1.3.2"

libraryDependencies ++= Seq(
  "io.spray" %% "spray-can" % sprayV,
  "io.spray" %% "spray-routing" % sprayV,
  "io.spray" %% "spray-client" % sprayV,
  "io.spray" %% "spray-json" % "1.3.0",
  "com.typesafe.akka" %% "akka-actor" % "2.3.6"
)