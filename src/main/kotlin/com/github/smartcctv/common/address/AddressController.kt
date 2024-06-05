package com.github.smartcctv.common.address

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/addr")
class AddressController(
    private val addressStore: AddressStore,
) {

    @GetMapping("/set/cam-api")
    fun setCamApiAddress(@RequestParam address: String): String {
        addressStore.camApi = address
        return address
    }

    @GetMapping("/set/cam-nginx")
    fun setCamNginxAddress(@RequestParam address: String): String {
        addressStore.camNginx = address
        return address
    }

    @GetMapping("/get/cam-api")
    fun getCamApiAddress(): String {
        return addressStore.camApi
    }

    @GetMapping("/get/cam-nginx")
    fun getCamNginxAddress(): String {
        return addressStore.camNginx
    }
}