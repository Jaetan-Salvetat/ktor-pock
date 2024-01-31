package fr.jaetan.helpers

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import fr.jaetan.models.User
import fr.jaetan.repositories.UserRepository

class JwtHelper {
    private val userRepository = UserRepository()

    fun verify(token: String): User? {
        val payload = try {
            JWT.require(Algorithm.HMAC256("my best secret")) // TODO: use env
                .build()
                .verify(token)
        } catch (_: Exception) {
            return null
        }

        val id = payload.getClaim("id").asInt()
            ?: return null
        val username = payload.getClaim("username").asString()
            ?: return null
        val user = userRepository.getUserById(id)
            ?: return null

        if (username != user.username) return null

        return user
    }

    fun generate(user: User): String = JWT.create()
        .withClaim("id", user.id)
        .withClaim("username", user.username)
        .sign(Algorithm.HMAC256("my best secret"))
}