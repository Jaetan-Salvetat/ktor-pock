package fr.jaetan.models.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateTaskRequest(
    val text: String,
    val isDone: Boolean
)
