package fr.jaetan.extensions

import fr.jaetan.helpers.JwtHelper
import fr.jaetan.models.User
import io.ktor.http.*
import io.ktor.server.request.*

fun ApplicationRequest.getUserFromToken(): User? {
    val token = header(HttpHeaders.Authorization)
        ?: return null
    return JwtHelper().verify(token)
}