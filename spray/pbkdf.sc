import java.util.UUID

import io.github.morgaroth.utils.spray.auth.AuthServiceTokenGenerator
import io.github.nremond.{SecureHash, PBKDF2}

val hasher: SecureHash = SecureHash()

val a = hasher.createHash("ala ma kota")

val b= AuthServiceTokenGenerator.defaultTokenGenerator.generateToken

