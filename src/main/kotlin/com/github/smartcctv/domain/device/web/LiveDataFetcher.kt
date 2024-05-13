package com.github.smartcctv.domain.device.web

import com.github.smartcctv.domain.device.business.LiveService
import com.github.smartcctv.domain.device.business.data.LiveCreation
import com.github.smartcctv.domain.device.persistence.Live
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation

@DgsComponent
class LiveDataFetcher(
    private val liveService: LiveService,
) {

    @DgsMutation
    fun startLive(creation: LiveCreation): Live {
        return liveService.create(creation)
    }
}
