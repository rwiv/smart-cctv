package com.github.cctvapi.domain.account.api

import com.github.cctvapi.domain.account.business.data.AccountResponse
import com.github.cctvapi.domain.account.business.data.AccountCreation
import com.github.cctvapi.domain.account.business.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    val accountService: AccountService,
) {

    @PostMapping("/signup")
    fun signup(@RequestBody accountCreation: AccountCreation): ResponseEntity<AccountResponse> {
        val user = accountService.create(accountCreation)
        return ResponseEntity.ok().body(AccountResponse.of(user))
    }
}
