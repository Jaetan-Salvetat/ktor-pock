package fr.jaetan.repositories

import fr.jaetan.models.Role
import fr.jaetan.models.User
import fr.jaetan.models.database.Users
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository {
    fun create(username: String, password: String): User? {
        return transaction {
            val query = Users.insert {
                it[Users.username] = username
                it[Users.password] = password
                it[roleId] = Role.User.id
            }

            val user = query.resultedValues?.first()
                ?: return@transaction null

            User(
                id = user[Users.id],
                username = user[Users.username]
            )
        }
    }
}