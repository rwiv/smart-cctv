package com.github.cctvapi.security.filters

import com.github.cctvapi.security.authentication.AuthenticationToken

data class LoginRequest(
    val username: String,
    val password: String,
) {
    companion object {
        fun of(requestedAuth: AuthenticationToken) = LoginRequest(
            username = requestedAuth.principal,
            password = requestedAuth.credentials!!
        )
    }
}
