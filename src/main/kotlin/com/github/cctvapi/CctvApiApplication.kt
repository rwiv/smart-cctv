package com.github.cctvapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CctvApiApplication

fun main(args: Array<String>) {
	runApplication<CctvApiApplication>(*args)
}
