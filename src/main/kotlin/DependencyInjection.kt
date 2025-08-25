package com.danielssonjacob

import com.danielssonjacob.v1.services.BenfordV1Service
import com.danielssonjacob.v1.services.BenfordV1ServiceImpl
import io.ktor.server.application.*
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton

fun Application.configureDI() {
    di { bind<BenfordV1Service>() with singleton { BenfordV1ServiceImpl() } }
}
