package fr.jaetan.routes

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
        route("api/users") {
            post("create") {
                val user = call.receive<RegisterRequest>()

                when (val result = service.create(user.username, user.password)) {
                    is UserExceptions.Success -> call.respond(
                        HttpStatusCode.Created,
                        FullUserResponse(result.user)
                    )
                    else -> call.respond(
                        HttpStatusCode.BadRequest,
                        ErrorResponse(result.message)
                    )
                }
            }
        }
    }
}