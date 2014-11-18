package io.github.morgaroth.utils.crypto

import io.github.morgaroth.utils.strings.{Base64Encoding, Base64Decoding}

trait AESWithBase64Decryptor extends AESDecryptions with Base64Decoding {

  def decryptFromBase64(encryptedData: String, password: String): Option[Array[Byte]] = encryptedData.fromBase64.decrypt(password)

  def decryptStringFromBase64(encryptedData: String, password: String): Option[String] = encryptedData.fromBase64.decrypt(password).map(new String(_))

}

trait AESWithBase64Encryptor extends AESEncryptions with Base64Encoding {

  def encryptToBase64(rawString: String, password: String): Option[String] = rawString.encrypt(password).map(_.toBase64)

}

trait AESWithBase64Operations extends AESWithBase64Encryptor with AESWithBase64Decryptor
