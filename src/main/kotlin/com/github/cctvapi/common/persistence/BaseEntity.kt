package com.github.cctvapi.common.persistence

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*

@MappedSuperclass
class BaseEntity(

    @Id
    @GeneratedValue(generator="uuid2")
    @GenericGenerator(name="uuid2", strategy="uuid2")
    @Column(columnDefinition="BINARY(16)")
    val id: UUID?,
)
