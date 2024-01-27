package fr.jaetan.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val username: String,
    val tasks: List<Task> = emptyList()
)
