package com.github.cctvapi.domain.account.persistence

import java.util.UUID

interface AccountRepositoryCustom {

    fun findByIdNotNull(id: UUID): Account
}