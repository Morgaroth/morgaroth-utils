import io.github.nremond.{SecureHash, PBKDF2}

val hasher: SecureHash = SecureHash()

val a = hasher.createHash("ala ma kota")