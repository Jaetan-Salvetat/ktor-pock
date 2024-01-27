package fr.jaetan.extensions

fun String.isValidUsername(): Boolean = this.length >= 2
fun String.isValidPassword(): Boolean = this.length >= 4