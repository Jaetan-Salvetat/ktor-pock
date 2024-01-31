package fr.jaetan.repositories

import fr.jaetan.models.Task
import fr.jaetan.models.database.Tasks
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class TaskRepository {
    fun create(text: String, isDone: Boolean, userId: Int): Task? {
        return transaction {
            val query = Tasks.insert {
                it[Tasks.text] = text
                it[Tasks.isDone] = isDone
                it[Tasks.userId] = userId
            }

            val task = query.resultedValues?.first()
                ?: return@transaction null

            Task(
                id = task[Tasks.id],
                text = task[Tasks.text],
                isDone = task[Tasks.isDone]
            )
        }
    }
}