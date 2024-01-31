package fr.jaetan.extensions

import at.favre.lib.crypto.bcrypt.BCrypt

typealias Password = String
private const val SALT = 12
fun Password.isValidPassword(): Boolean = this.length >= 4

fun Password.hash(): String = BCrypt.withDefaults()
    .hashToString(SALT, this.toCharArray())

fun Password.verify(basicPassword: Password): Boolean = BCrypt.verifyer()
    .verify(this.toCharArray(), basicPassword.toCharArray())
    .verified
