package fr.jaetan.repositories

import fr.jaetan.extensions.Password
import fr.jaetan.extensions.verify
import fr.jaetan.models.User
import org.jetbrains.exposed.sql.transactions.transaction

class AuthRepository {
    private val userRepository = UserRepository()

    fun login(username: String, password: Password): User? {
        return transaction {
            val (user, ashedPassword) = userRepository.getByUsername(username)
                ?: return@transaction null

            if (password.verify(ashedPassword)) {
                user
            } else {
                null
            }
        }
    }
}