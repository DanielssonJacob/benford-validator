package com.danielssonjacob

import io.ktor.server.application.*
import io.ktor.server.plugins.calllogging.*

fun Application.configureLogging() {
    install(CallLogging)
}
