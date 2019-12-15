import sbt.Keys.{resolvers, scalaVersion}

sonatypeSettings

name := "utils"

val commonSettings = Seq(
  organization := ThisProject.organization,
  version := ThisProject.projectVersion,
  pomExtra := ThisProject.commonPomFile,
  publishTo := ThisProject.publishTo,
  publishMavenStyle := true,
  scalaVersion := "2.12.2",
  scalacOptions ++= Seq("-feature"),
  scalacOptions in Test ++= Seq("-Yrangepos"),
  resolvers ++= Seq(
    "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/"
  )
)

lazy val base = project.settings(commonSettings: _*)

lazy val mongodb = project.settings(commonSettings: _*).settings(
  name := s"${ThisProject.projectName}-mongodb",
  libraryDependencies ++= Seq(
    "com.github.salat" %% "salat-core" % "1.11.1",
    "com.github.salat" %% "salat-util" % "1.11.1",
    ThisProject.Deps.Ficus,
    ThisProject.Deps.TypesafeCfg
  )
)

lazy val mongodb_testing = project.settings(commonSettings: _*).settings(
  name := s"${ThisProject.projectName}-mongodb-test",
  libraryDependencies ++= Seq(
    "com.github.salat" %% "salat-core" % "1.11.1",
    "com.github.salat" %% "salat-util" % "1.11.1",
    ThisProject.Deps.Ficus,
    "org.specs2" %% "specs2-core" % "3.9.1",
    ThisProject.Deps.TypesafeCfg
  )
)

lazy val crypto = project.dependsOn(base).settings(commonSettings: _*)

//lazy val spray = project.dependsOn(mongodb, base)

lazy val akka = project.settings(commonSettings: _*)

lazy val morgaroth = project.in(file("."))
  .aggregate(base, crypto, mongodb, akka, mongodb_testing)
  .settings(
    publishArtifact := false
  )
