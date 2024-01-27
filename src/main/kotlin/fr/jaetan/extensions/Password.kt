package fr.jaetan.extensions

import at.favre.lib.crypto.bcrypt.BCrypt
import java.security.SecureRandom
import java.security.spec.KeySpec
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

typealias Password = String
private const val SECRET = "my best random secret"
private const val SALT = 12
fun Password.isValidPassword(): Boolean = this.length >= 4

fun Password.hash(): String = BCrypt.withDefaults()
    .hashToString(SALT, this.toCharArray())

fun Password.verify(basicPassword: String): Boolean = BCrypt.verifyer()
    .verify(basicPassword.toCharArray(), this.toCharArray())
    .verified
