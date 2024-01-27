package fr.jaetan.services

import fr.jaetan.extensions.isValidPassword
import fr.jaetan.extensions.isValidUsername
import fr.jaetan.models.User
import fr.jaetan.repositories.UserRepository

class UserService {
    private val repository = UserRepository()

    fun create(username: String, password: String): UserExceptions {
        if (!username.isValidUsername()) return UserExceptions.InvalidUsername
        if (!password.isValidPassword()) return UserExceptions.InvalidPassword

        val user = repository.create(username, password)
            ?: return UserExceptions.Unknown

        return UserExceptions.Success(user)
    }
}

sealed class UserExceptions(open val message: String) {
    data object InvalidUsername: UserExceptions("invalid_username")
    data object InvalidPassword: UserExceptions("invalid_password")
    data object Unknown: UserExceptions("unknown")

    data class Success(val user: User) : UserExceptions("")
}