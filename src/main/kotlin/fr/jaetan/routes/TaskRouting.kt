package fr.jaetan.routes

import fr.jaetan.extensions.getUserFromToken
import fr.jaetan.models.request.CreateTaskRequest
import fr.jaetan.models.responses.ErrorResponse
import fr.jaetan.models.responses.TaskResponse
import fr.jaetan.services.TaskExceptions
import fr.jaetan.services.TaskService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.taskRouting() {
    val taskService = TaskService()

    routing {
        route("tasks") {
            post {
                val task = call.receive<CreateTaskRequest>()
                val user = call.request.getUserFromToken()
                    ?: return@post call.respond(HttpStatusCode.Unauthorized, ErrorResponse(message = "bad_token"))

                when (val result = taskService.create(task.text, task.isDone, user.id)) {
                    is TaskExceptions.Success -> call.respond(
                        status = HttpStatusCode.Created,
                        message = TaskResponse(result.task)
                    )
                    else -> call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = ErrorResponse("An unknown error has occurred")
                    )
                }
            }
        }
    }
}