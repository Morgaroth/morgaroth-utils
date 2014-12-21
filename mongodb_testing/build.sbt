
name := s"${ThisProject.projectName}-mongodb-test"

version := ThisProject.projectVersion

organization := ThisProject.organization

scalacOptions ++= Seq("-feature")

pomExtra := ThisProject.commonPomFile

publishTo := ThisProject.publishTo

val ficusDependency = scalaVersion {
  case "2.11.4" | "2.11.2" | "2.11.0" =>
    "net.ceedubs" %% "ficus" % "1.1.1" withSources()
  case _ =>
    "net.ceedubs" %% "ficus" % "1.0.1" exclude("com.typesafe", "config") withSources()
}

libraryDependencies ++= Seq(
  "com.novus"     %% "salat"  % "1.9.9" withSources(),
  "com.typesafe"  %  "config" % "1.2.1" withSources(),
  "org.specs2"    %% "specs2" % "2.2.3" withSources()
)

libraryDependencies <+= ficusDependency