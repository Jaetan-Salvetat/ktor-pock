package fr.jaetan.models.request

import fr.jaetan.extensions.Password
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val username: String,
    val password: Password
)
