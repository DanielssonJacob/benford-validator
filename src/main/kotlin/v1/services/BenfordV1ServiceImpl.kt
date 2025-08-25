package com.danielssonjacob.v1.services

import com.danielssonjacob.v1.models.requests.BenfordAnalyzeV1Request
import com.danielssonjacob.v1.models.responses.BenfordAnalyzeV1Response
import org.apache.commons.math3.stat.inference.ChiSquareTest

class BenfordV1ServiceImpl : BenfordV1Service {

    private val chiSquareTest = ChiSquareTest()

    private val benfordProbabilities =
        mapOf(
            1 to 0.301,
            2 to 0.176,
            3 to 0.125,
            4 to 0.097,
            5 to 0.079,
            6 to 0.067,
            7 to 0.058,
            8 to 0.051,
            9 to 0.046,
        )

    override fun analyze(request: BenfordAnalyzeV1Request): BenfordAnalyzeV1Response {
        val digits = extractLeadingDigits(request.text)
        if (digits.isEmpty()) {
            return BenfordAnalyzeV1Response(
                expectedDistribution = benfordProbabilities,
                actualDistribution = benfordProbabilities.mapValues { 0.0 },
                chiSquareValue = 0.0,
                isSignificant = false,
            )
        }

        val totalCount = digits.size.toDouble()
        val countPerDigit = getCountByDigit(digits)
        val expectedCounts = calculateExpectedCounts(totalCount)
        val observedCounts = countPerDigit.values.toLongArray()

        val chiSquareValue = chiSquareTest.chiSquare(expectedCounts, observedCounts)

        val isSignificant =
            chiSquareTest.chiSquareTest(expectedCounts, observedCounts, request.significanceLevel)

        return BenfordAnalyzeV1Response(
            expectedDistribution = benfordProbabilities,
            actualDistribution = countPerDigit.mapValues { (_, count) -> count / totalCount },
            chiSquareValue = chiSquareValue,
            isSignificant = isSignificant,
        )
    }

    private fun calculateExpectedCounts(total: Double): DoubleArray =
        benfordProbabilities.values.map { it * total }.toDoubleArray()

    private fun extractLeadingDigits(text: String): List<Int> {
        val regex = Regex("""\d+(\.\d+)?""")
        return regex
            .findAll(text)
            .mapNotNull { match ->
                match.value.trimStart('0').firstOrNull { it.isDigit() && it != '0' }?.digitToInt()
            }
            .filter { it in 1..9 }
            .toList()
    }

    private fun getCountByDigit(observations: List<Int>): Map<Int, Long> =
        (1..9).associateWith { digit -> observations.count { it == digit }.toLong() }
}
