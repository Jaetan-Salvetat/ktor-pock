package fr.jaetan.models

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: String,
    val text: String
)
