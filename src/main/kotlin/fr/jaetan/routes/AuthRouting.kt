package fr.jaetan.routes

import fr.jaetan.models.request.LoginRequest
import fr.jaetan.models.responses.ErrorResponse
import fr.jaetan.models.responses.FullUserResponse
import fr.jaetan.services.AuthExceptions
import fr.jaetan.services.AuthService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRouting() {
    val service = AuthService()

    routing {
        route("api/auth") {
            post("login") {
                val credentials = call.receive<LoginRequest>()

                when (val result = service.login(credentials.username, credentials.password)) {
                    is AuthExceptions.Success -> call.respond(HttpStatusCode.OK, FullUserResponse(result.user))
                    else -> call.respond(HttpStatusCode.Unauthorized, ErrorResponse(result.message))
                }
            }
        }
    }
}