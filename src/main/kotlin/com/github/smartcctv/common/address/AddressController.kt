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

    @GetMapping("/set/api")
    fun setApiAddress(@RequestParam address: String): String {
        addressStore.apiAddress = address
        return address
    }

    @GetMapping("/set/cam")
    fun setCamAddress(@RequestParam address: String): String {
        addressStore.camAddress = address
        return address
    }

    @GetMapping("/get/api")
    fun getApiAddress(@RequestParam address: String): String {
        return addressStore.apiAddress
    }

    @GetMapping("/get/cam")
    fun getCamAddress(@RequestParam address: String): String {
        return addressStore.camAddress
    }
}