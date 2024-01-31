package fr.jaetan.models

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Int,
    val text: String,
    val isDone: Boolean
)
