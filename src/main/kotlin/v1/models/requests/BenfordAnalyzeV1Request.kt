package com.danielssonjacob.v1.models.requests

import kotlinx.serialization.Serializable

@Serializable
data class BenfordAnalyzeV1Request(val text: String, val significanceLevel: Double) {
    init {
        require(significanceLevel in 0.0..0.5) { "significanceLevel must be between 0 and 0.5" }
    }
}
