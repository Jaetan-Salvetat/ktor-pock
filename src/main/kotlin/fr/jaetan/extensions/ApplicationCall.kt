package fr.jaetan.extensions

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

suspend inline fun <reified T : Any> ApplicationCall.respond(
    status: HttpStatusCode,
    message: T,
    headers: Map<String, String>
) {
    headers.forEach { (key, value) ->
        this.response.header(key, value)
    }

    respond(status, message)
}