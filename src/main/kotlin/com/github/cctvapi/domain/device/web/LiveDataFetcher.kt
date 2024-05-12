package com.github.cctvapi.domain.device.web

import com.github.cctvapi.domain.device.business.LiveService
import com.github.cctvapi.domain.device.business.data.LiveCreation
import com.github.cctvapi.domain.device.persistence.Live
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
