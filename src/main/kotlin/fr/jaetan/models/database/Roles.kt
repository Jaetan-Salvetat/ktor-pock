package fr.jaetan.models.database

import org.jetbrains.exposed.sql.Table

object Roles: Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 25)

    override val primaryKey = PrimaryKey(id)
}