package fr.jaetan.models.database

import org.jetbrains.exposed.sql.Table

object Users: Table() {
    val id = integer("id").autoIncrement()
    val username = varchar("username", 25)
    val password = varchar("password", 255)
    val roleId = integer("roleId").references(Roles.id)

    override val primaryKey = PrimaryKey(id)
}