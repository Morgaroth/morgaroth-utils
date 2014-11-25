name := s"${ThisProject.projectName}-crypto"

version := ThisProject.projectVersion

organization := ThisProject.organization

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  "org.cryptonode.jncryptor" % "jncryptor" % "1.0.1"
)

pomExtra := ThisProject.commonPomFile

publishTo := ThisProject.publishTo
