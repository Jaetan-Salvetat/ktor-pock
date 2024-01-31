package fr.jaetan.services

import fr.jaetan.models.Task
import fr.jaetan.repositories.TaskRepository

class TaskService {
    val taskRepository = TaskRepository()

    fun create(text: String, isDone: Boolean, userId: Int): TaskExceptions {
        val task = taskRepository.create(text, isDone, userId)
            ?: return TaskExceptions.Unknown

        return TaskExceptions.Success(task)
    }
}

sealed class TaskExceptions(open val message: String = "") {
    data object Unknown: TaskExceptions("An unknown error as occurred")
    data class Success(val task: Task): TaskExceptions()
}