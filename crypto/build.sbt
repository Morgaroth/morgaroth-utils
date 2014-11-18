
name := s"${ThisProject.projectName}-crypto"

version := ThisProject.projectVersion

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  "org.cryptonode.jncryptor"  %   "jncryptor"       % "1.0.1"
)

pomExtra := <url>https://github.com/Morgaroth/morgaroth-utils-crypto</url>
  <licenses>
    <license>
      <name>BSD-style</name>
      <url>http://www.opensource.org/licenses/bsd-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:Morgaroth/morgaroth-utils-crypto.git</url>
    <connection>scm:git:git@github.com:Morgaroth/morgaroth-utils-crypto.git</connection>
  </scm>
  <developers>
    <developer>
      <id>$DEVELOPER_ID</id>
      <name>$DEVELOPER_FULL_NAME</name>
    </developer>
  </developers>