package com.github.smartcctv.domain.device.persistence

import com.github.smartcctv.common.persistence.BaseTimeEntity
import com.github.smartcctv.domain.account.persistence.Account
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

    @OneToOne(mappedBy = "device", cascade = [CascadeType.REMOVE])
    val live: Live? = null,

    @Column(nullable = true)
    val accessKey: String? = null,

    id: UUID? = null,
) : BaseTimeEntity(id)
