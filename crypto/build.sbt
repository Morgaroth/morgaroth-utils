name := s"${ThisProject.projectName}-crypto"

version := ThisProject.projectVersion

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  "org.cryptonode.jncryptor" % "jncryptor" % "1.0.1"
)