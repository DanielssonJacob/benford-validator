package com.danielssonjacob.v1.models.responses

import kotlinx.serialization.Serializable

@Serializable
data class BenfordAnalyzeV1Response(
    val expectedDistribution: Map<Int, Double>,
    val actualDistribution: Map<Int, Double>,
    val chiSquareValue: Double,
    val isSignificant: Boolean,
)
