package com.github.smartcctv.domain.device.persistence

import com.github.smartcctv.common.persistence.BaseTimeEntity
import com.github.smartcctv.domain.account.persistence.Account
import com.github.smartcctv.domain.live.persistence.Live
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "iot_device")
class IotDevice (

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    val owner: Account,

    @Column(nullable = false)
    val name: String,


    @Column(nullable = false, unique = true)
    var streamKey: String,

    id: UUID? = null,
) : BaseTimeEntity(id) {

    @OneToOne(mappedBy = "device", cascade = [CascadeType.REMOVE])
    var live: Live? = null
        protected set

    fun updateLive(l: Live) {
        live = l
    }
}
