package fr.jaetan.models.responses

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    override val message: String,
    override val success: Boolean = false
): IResponse()
