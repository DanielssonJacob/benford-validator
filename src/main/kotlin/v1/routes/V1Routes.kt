package com.danielssonjacob.v1.routes

import io.ktor.server.routing.*

fun Route.v1Routes() {
    route("v1") { route("distribution") { benfordRoutes() } }
}
