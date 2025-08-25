package com.danielssonjacob.v1.routes

import com.danielssonjacob.v1.models.requests.BenfordAnalyzeV1Request
import com.danielssonjacob.v1.services.BenfordV1Service
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.benfordRoutes() {
    val benfordService: BenfordV1Service by closestDI().instance()
    route("benford") {
        post("analyze") {
            val request = call.receive<BenfordAnalyzeV1Request>()
            val response = benfordService.analyze(request)
            call.respond(response)
        }
    }
}
