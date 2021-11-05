package com.fourthwall.moviesapi.controller.health

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthController {

    @GetMapping
    fun check(): String {
        return "<h2>I am working well...</h2>"
    }
}