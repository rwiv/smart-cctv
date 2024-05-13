package com.github.smartcctv.domain.account.persistence

import java.util.UUID

interface AccountRepositoryCustom {

    fun findByIdNotNull(id: UUID): Account
}