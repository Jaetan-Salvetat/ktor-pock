package fr.jaetan.models.database

import org.jetbrains.exposed.sql.Table

object Tasks: Table() {
    val id = integer("id").autoIncrement()
    val text = text("text")
    val isDone = bool("isDone")
    val userId = integer("userId").references(Users.id)

    override val primaryKey = PrimaryKey(id)
}