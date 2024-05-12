package com.github.cctvapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class CctvApiApplication

fun main(args: Array<String>) {
	runApplication<CctvApiApplication>(*args)
}
