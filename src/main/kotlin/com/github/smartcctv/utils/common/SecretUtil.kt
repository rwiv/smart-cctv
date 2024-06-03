package com.github.smartcctv.utils.common

import java.nio.file.Path
import kotlin.io.path.pathString

object SecretUtil {

    data class EmailSecret(val username: String, val password: String, val testEmail: String)
    data class ServerSecret(val email: EmailSecret)

    fun getSecret(): ServerSecret {
        val root = Path.of(PathUtil.getRoot().pathString, "secret", "conf.json")
        return JsonUtil.readJsonFile<ServerSecret>(root)
    }
}
