package com.danielssonjacob

import com.danielssonjacob.v1.routes.v1Routes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing { v1Routes() }
}
