package fr.jaetan.services

import fr.jaetan.models.User
import fr.jaetan.repositories.UserRepository

class UserService {
    private val repository = UserRepository()

    fun create(username: String, password: String): UserExceptions {
        if (repository.getByUsername(username) != null) return UserExceptions.UserAlreadyExist

        val user = repository.create(username, password)
            ?: return UserExceptions.Unknown

        return UserExceptions.Success(user)
    }
}

sealed class UserExceptions(open val message: String = "") {
    data object UserAlreadyExist: UserExceptions("user_already_exist")
    data object Unknown: UserExceptions("unknown")

    data class Success(val user: User) : UserExceptions()
}