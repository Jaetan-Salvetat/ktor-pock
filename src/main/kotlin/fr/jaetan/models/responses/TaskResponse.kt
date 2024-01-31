package fr.jaetan.models.responses

import fr.jaetan.models.Task
import kotlinx.serialization.Serializable

@Serializable
data class TaskResponse(
    val task: Task
)
