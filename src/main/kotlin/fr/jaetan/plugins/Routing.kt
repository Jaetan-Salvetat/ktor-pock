package fr.jaetan.plugins

import fr.jaetan.routes.userRouting
import io.ktor.server.application.*

fun Application.configureRouting() {
    userRouting()
}
