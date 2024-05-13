package com.github.smartcctv.domain.account.persistence

import com.github.smartcctv.common.error.exception.NotFoundException
import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Repository
class AccountRepositoryCustomImpl(em: EntityManager) : AccountRepositoryCustom {

    private val repo = SimpleJpaRepository<Account, UUID>(Account::class.java, em)

    override fun findByIdNotNull(id: UUID): Account {
        return repo.findById(id).getOrNull()
            ?: throw NotFoundException("not found account")
    }
}
