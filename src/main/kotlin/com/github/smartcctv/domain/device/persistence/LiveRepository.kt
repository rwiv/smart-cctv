package com.github.smartcctv.domain.device.persistence

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LiveRepository :JpaRepository<Live, UUID> {
}
