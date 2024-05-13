package com.github.smartcctv.domain.video.peresistence

import java.util.*

interface VideoRepositoryCustom {

    fun findByIdNotNull(id: UUID): Video
}