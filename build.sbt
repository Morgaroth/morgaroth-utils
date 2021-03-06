sonatypeSettings

organization := ThisProject.organization

publishMavenStyle := true

name := "utils"

version := ThisProject.projectVersion

scalaVersion := "2.10.4"

crossScalaVersions := Seq(
  "2.10.4",
  "2.11.4"
)

resolvers ++= Seq(
  "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/"
)

lazy val base = project

lazy val mongodb = project

lazy val mongodb_testing = project

lazy val crypto = project.dependsOn(base)

lazy val spray = project.dependsOn(mongodb, base)

lazy val akka = project

lazy val morgaroth = Project(
  id = "morgaroth",
  base = file("."),
  aggregate = Seq(base, crypto, spray, mongodb, akka, mongodb_testing),
  settings = Project.defaultSettings ++ Seq(
    publishArtifact := false
  )
)
