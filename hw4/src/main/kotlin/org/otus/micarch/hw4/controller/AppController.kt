package org.otus.micarch.hw4.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/app")
class AppController {

    @GetMapping("/version")
    fun getAppVersion(): String {
        return System.getenv()["APP_VERSION"]!!
    }

}