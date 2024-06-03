package com.github.smartcctv.email

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/email")
class EmailController(
    private val emailService: EmailService,
) {

    @GetMapping("/send")
    fun sendEmail(
        @RequestParam email: String,
        @RequestParam username: String,
        @RequestParam password: String,
    ) {
        emailService.sendEmail(email, username, password)
    }
}