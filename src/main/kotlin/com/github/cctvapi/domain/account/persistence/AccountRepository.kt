package com.github.cctvapi.domain.account.persistence

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AccountRepository : JpaRepository<Account, UUID> {

    fun findByUsername(username: String): Account?
    fun findAllBy(pageable: Pageable): Page<Account>
}