package fr.jaetan.repositories

import fr.jaetan.extensions.Password
import fr.jaetan.extensions.hash
import fr.jaetan.models.Role
import fr.jaetan.models.User
import fr.jaetan.models.database.Users
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository {
    fun create(username: String, password: String): User? {
        return transaction {
            val query = Users.insert {
                it[Users.username] = username
                it[Users.password] = password.hash()
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

    fun getUserById(id: Int): User? {
        return transaction {
            val query = Users.select {
                Users.id eq id
            }.firstOrNull()
                ?: return@transaction null

            User(
                id = query[Users.id],
                username = query[Users.username]
            )
        }
    }

    fun getByUsername(username: String): Pair<User, Password>? {
        return transaction {
            val query = Users.select {
                Users.username eq username
            }.firstOrNull()
                ?: return@transaction null

            Pair(
                first = User(
                    id = query[Users.id],
                    username = query[Users.username]
                ),
                second = query[Users.password]
            )
        }
    }
}