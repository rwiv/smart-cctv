package com.github.smartcctv.domain.video.peresistence

import com.github.smartcctv.domain.device.persistence.IotDevice
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface VideoRepository : JpaRepository<Video, UUID>, VideoRepositoryCustom {

    fun findByDevice(device: IotDevice): List<Video>
}
