package fr.jaetan.routes

import fr.jaetan.extensions.respond
import fr.jaetan.helpers.JwtHelper
import fr.jaetan.models.request.RegisterRequest
import fr.jaetan.models.responses.ErrorResponse
import fr.jaetan.models.responses.FullUserResponse
import fr.jaetan.services.UserExceptions
import fr.jaetan.services.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.userRouting() {
    val service = UserService()

    routing {
        route("users") {
            post("create") {
                val user = call.receive<RegisterRequest>()
                val jwt = JwtHelper()

                when (val result = service.create(user.username, user.password)) {
                    is UserExceptions.Success -> {
                        call.respond(
                            status = HttpStatusCode.Created,
                            message = FullUserResponse(result.user),
                            headers = mapOf(HttpHeaders.Authorization to jwt.generate(result.user))
                        )
                    }
                    else -> call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = ErrorResponse(result.message)
                    )
                }
            }
        }
    }
}
