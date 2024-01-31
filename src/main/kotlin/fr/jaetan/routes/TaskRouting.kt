package fr.jaetan.routes

import fr.jaetan.extensions.getUserFromToken
import fr.jaetan.models.request.CreateTaskRequest
import fr.jaetan.models.responses.ErrorResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.taskRouting() {
    routing {
        route("tasks") {
            post {
                val task = call.receive<CreateTaskRequest>()
                val user = call.request.getUserFromToken()
                    ?: return@post call.respond(HttpStatusCode.Unauthorized, ErrorResponse(message = "bad_token"))

                call.respond(HttpStatusCode.Created)
            }
        }
    }
}