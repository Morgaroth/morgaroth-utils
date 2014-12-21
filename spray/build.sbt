name := s"${ThisProject.projectName}-spray"

version := ThisProject.projectVersion

scalacOptions ++= Seq("-feature")

organization := ThisProject.organization

pomExtra := ThisProject.commonPomFile

publishTo := ThisProject.publishTo

val sprayV = "1.3.2"

val basicDependencies: Seq[ModuleID] = Seq(
  "io.spray"          %% "spray-can"      % sprayV withSources(),
  "io.spray"          %% "spray-routing"  % sprayV withSources(),
  "io.spray"          %% "spray-client"   % sprayV withSources(),
  "io.spray"          %% "spray-json"     % "1.3.0" withSources(),
  "com.typesafe.akka" %% "akka-actor"     % "2.3.6" withSources(),
  "io.github.nremond" %% "pbkdf2-scala"   % "0.4" withSources()
)

val testDependencies: Seq[ModuleID] = Seq(
  "io.spray"      %% "spray-testkit" % sprayV withSources(),
  "org.scalatest" %% "scalatest"     % "2.2.1" withSources()
).map(_ % "test")

libraryDependencies ++= (basicDependencies ++ testDependencies)

conflictManager := ConflictManager.latestTime
