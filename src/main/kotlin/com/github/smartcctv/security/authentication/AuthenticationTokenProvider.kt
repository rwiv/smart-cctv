package com.github.smartcctv.security.authentication

import com.github.smartcctv.domain.account.business.data.AccountResponse
import com.github.smartcctv.domain.account.business.AccountService
import com.github.smartcctv.common.error.exception.HttpException
import com.github.smartcctv.common.error.exception.NotFoundException
import com.github.smartcctv.security.userdetails.AccountDetails
import com.github.smartcctv.security.userdetails.AccountDetailsService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AuthenticationTokenProvider(
    val accountService: AccountService,
    val accountDetailsService: AccountDetailsService,
    val passwordEncoder: PasswordEncoder,
) : AuthenticationProvider {

    @Transactional
    override fun authenticate(authentication: Authentication): Authentication {
        val username = (authentication as AuthenticationToken).principal
        val password = authentication.credentials
        val accountDetails: AccountDetails = accountDetailsService.loadUserByUsername(username)

        if (!passwordEncoder.matches(password, accountDetails.password)) {
            throw HttpException(401, "Invalid password")
        }

        val account = accountService.findById(accountDetails.id)
            ?: throw NotFoundException("not found account")
        val accountResponse = AccountResponse.of(account)
        return AuthenticationToken.successToken(
            accountDetails.username,
            accountDetails.authorities,
            accountResponse,
        )
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == AuthenticationToken::class.java
    }
}