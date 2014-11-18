import ThisProject.PomData

name := s"${ThisProject.projectName}-crypto"

version := ThisProject.projectVersion

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  "org.cryptonode.jncryptor" % "jncryptor" % "1.0.1"
)

pomExtra := ThisProject.generatePomFile(
  PomData("https://github.com/Morgaroth/morgaroth-utils-crypto", "git@github.com:Morgaroth/morgaroth-utils-crypto.git")
)