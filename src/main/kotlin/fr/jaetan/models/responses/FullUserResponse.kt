package fr.jaetan.models.responses

import fr.jaetan.models.User
import kotlinx.serialization.Serializable

@Serializable
data class FullUserResponse(
    val user: User
): IResponse()
