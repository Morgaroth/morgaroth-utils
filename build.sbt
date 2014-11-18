
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

lazy val base = project

lazy val crypto = project.dependsOn(base)

lazy val root = Project(
  id        = "root",
  base      = file("."),
  aggregate = Seq(base, crypto),
  settings  = Project.defaultSettings ++ Seq(
    publishArtifact := false
  )
)