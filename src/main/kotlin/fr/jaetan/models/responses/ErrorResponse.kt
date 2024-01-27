package fr.jaetan.models.responses

data class ErrorResponse(
    override val message: String,
    override val success: Boolean = false
): IResponse()
