name := s"${ThisProject.projectName}-mongodb"

version := ThisProject.projectVersion

scalacOptions ++= Seq("-feature")

val ficusDependency = scalaVersion {
  case "2.11.0" => "net.ceedubs" %% "ficus" % "1.1.1"
  case _ => "net.ceedubs" %% "ficus" % "1.0.1"
}

libraryDependencies += "com.novus" %% "salat" % "1.9.9"

libraryDependencies <+= ficusDependency
