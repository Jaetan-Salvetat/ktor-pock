package fr.jaetan.routes

import fr.jaetan.extensions.respond
import fr.jaetan.helpers.JwtHelper
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
        route("auth") {
            post("login") {
                val credentials = call.receive<LoginRequest>()
                val jwt = JwtHelper()

                when (val result = service.login(credentials.username, credentials.password)) {
                    is AuthExceptions.Success -> call.respond(
                        status = HttpStatusCode.OK,
                        message = FullUserResponse(result.user),
                        headers = mapOf(HttpHeaders.Authorization to jwt.generate(result.user))
                    )
                    else -> call.respond(HttpStatusCode.Unauthorized, ErrorResponse(result.message))
                }
            }
        }
    }
}