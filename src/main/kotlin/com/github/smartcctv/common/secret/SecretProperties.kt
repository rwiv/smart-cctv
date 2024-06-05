package com.github.smartcctv.common.secret

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SecretProperties {

    @Value("\${secret.account.admin.username}")
    lateinit var adminUsername: String

    @Value("\${secret.account.admin.password}")
    lateinit var adminPassword: String

    @Value("\${secret.address.cam.api}")
    lateinit var camApiAddr: String

    @Value("\${secret.address.cam.nginx}")
    lateinit var camNginxAddr: String
}