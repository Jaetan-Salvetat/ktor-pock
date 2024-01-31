package fr.jaetan.plugins

import fr.jaetan.models.Role
import fr.jaetan.models.database.Roles
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureMySql() {
    Database.connect(
        url = "jdbc:mysql://localhost:3306/KtorPock",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "root"
    )

    createTables()
    initializeRoles()
}

private fun createTables() {
    transaction {
        exec("CREATE TABLE IF NOT EXISTS Roles (id INTEGER PRIMARY KEY, name VARCHAR(25) UNICODE)")
        exec("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY AUTO_INCREMENT, username VARCHAR(25) UNIQUE, password VARCHAR(255), roleId integer, FOREIGN KEY (roleId) REFERENCES Roles(id))")
        exec("CREATE TABLE IF NOT EXISTS Tasks (id INTEGER PRIMARY KEY AUTO_INCREMENT, text TEXT, idDone BOOLEAN, userId INTEGER, FOREIGN KEY (userId) REFERENCES users(id))")

    }
}

private fun initializeRoles() {
    transaction {
        if (Roles.select { Op.TRUE }.count() < 2) {
            Role.entries.forEach { role ->
                Roles.insert {
                    it[id] = role.id
                    it[name] = role.name
                }
            }
        }
    }
}
