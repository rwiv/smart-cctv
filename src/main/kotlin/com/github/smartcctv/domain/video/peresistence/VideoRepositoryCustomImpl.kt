package com.github.smartcctv.domain.video.peresistence

import com.github.smartcctv.common.error.exception.NotFoundException
import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Repository
class VideoRepositoryCustomImpl(em: EntityManager) : VideoRepositoryCustom {

    private val repo = SimpleJpaRepository<Video, UUID>(Video::class.java, em)

    override fun findByIdNotNull(id: UUID): Video {
        return repo.findById(id).getOrNull()
            ?: throw NotFoundException("not found video")
    }
}
