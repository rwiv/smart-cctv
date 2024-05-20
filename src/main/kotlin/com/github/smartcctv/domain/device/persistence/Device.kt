package com.github.smartcctv.domain.device.persistence

import com.github.smartcctv.utils.persistence.BaseTimeEntity
import com.github.smartcctv.domain.account.persistence.Account
import com.github.smartcctv.domain.live.persistence.Live
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "device", indexes = [
    Index(name = "idx_stream_key", columnList = "stream_key"),
])
class Device (

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
}
