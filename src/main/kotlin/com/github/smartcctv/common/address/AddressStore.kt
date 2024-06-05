package com.github.smartcctv.common.address

import com.github.smartcctv.common.secret.SecretProperties
import org.springframework.stereotype.Component

@Component
class AddressStore(
    secretProperties: SecretProperties,
) {

    var camApi: String = secretProperties.camApiAddr
    var camNginx: String = secretProperties.camNginxAddr
}
