package fr.jaetan.helpers

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import fr.jaetan.repositories.UserRepository

class JwtHelper {
    private val userRepository = UserRepository()

    fun verify(token: String): Boolean {
        val payload = JWT.require(Algorithm.HMAC256("my best secret")) // TODO: use env
            .build()
            .verify(token)
        val id = payload.getClaim("id").asInt()
            ?: return false
        val username = payload.getClaim("username").asString()
            ?: return false
        val user = userRepository.getUserById(id)
            ?: return false

        return user.username == username
    }

    fun generate(username: String, id: Int): String = JWT.create()
        .withClaim("id", id)
        .withClaim("username", username)
        .sign(Algorithm.HMAC256("my best secret"))
}