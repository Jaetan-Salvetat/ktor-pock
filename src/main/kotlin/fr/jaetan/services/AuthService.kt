package fr.jaetan.services

import fr.jaetan.extensions.Password
import fr.jaetan.models.User
import fr.jaetan.repositories.AuthRepository

class AuthService {
    private val repository = AuthRepository()

    fun login(username: String, password: Password): AuthExceptions {
        val user = repository.login(username, password)
            ?: return AuthExceptions.BadCredentials

        return AuthExceptions.Success(user)
    }
}

sealed class AuthExceptions(open val message: String = "") {
    data object BadCredentials: AuthExceptions("bad_credentials")
    data class Success(val user: User): AuthExceptions()
}