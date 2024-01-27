package fr.jaetan.routes

import fr.jaetan.models.request.CreateTaskRequest
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.taskRouting() {
    routing {
        route("tasks") {
            post {
                val task = call.receive<CreateTaskRequest>()


            }
        }
    }
}