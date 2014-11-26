import ThisProject.PomData

sonatypeSettings

organization := "io.github.morgaroth"

publishMavenStyle := true

name := "utils"

version := ThisProject.projectVersion

scalaVersion := "2.10.4"

crossScalaVersions := Seq(
  "2.10.4",
  "2.11.0"
)

resolvers ++= Seq(
  "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/"
)

pomExtra := ThisProject.generatePomFile(
  PomData("https://github.com/Morgaroth/morgaroth-utils", "git@github.com:Morgaroth/morgaroth-utils.git")
)

lazy val base = project

lazy val mongodb = project

lazy val crypto = project.dependsOn(base)

lazy val spray = project.dependsOn(mongodb, base)

lazy val akka = project

lazy val root = Project(
  id = "morgaroth",
  base = file("."),
  aggregate = Seq(base, crypto, spray, mongodb, akka),
  settings = Project.defaultSettings ++ Seq(
    publishArtifact := false
  )
)
