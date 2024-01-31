package fr.jaetan.plugins

import fr.jaetan.extensions.isValidPassword
import fr.jaetan.extensions.isValidUsername
import fr.jaetan.models.request.CreateTaskRequest
import fr.jaetan.models.request.LoginRequest
import fr.jaetan.models.request.RegisterRequest
import fr.jaetan.models.responses.ErrorResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureValidation() {
    // Handle generics exceptions
    install(StatusPages) {
        exception<BadRequestException> { call, cause ->
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = ErrorResponse(message = cause.message ?: "An error has occurred"))
        }
    }

    // Handle custom exceptions
    install(RequestValidation) {
        validate<LoginRequest> {
            when {
                !it.username.isValidUsername() -> ValidationResult.Invalid("username length should be not empty")
                !it.password.isValidPassword() -> ValidationResult.Invalid("password length should be greeter that 3")
                else -> ValidationResult.Valid
            }
        }
        validate<RegisterRequest> {
            when {
                !it.username.isValidUsername() -> ValidationResult.Invalid("username should be not empty")
                !it.password.isValidPassword() -> ValidationResult.Invalid("password length should be greeter that 3")
                else -> ValidationResult.Valid
            }
        }
        validate<CreateTaskRequest> {
            when {
                it.text.isEmpty() -> ValidationResult.Invalid("task text should be not empty")
                else -> ValidationResult.Valid
            }
        }
    }
}