package com.danielssonjacob.v1.services

import com.danielssonjacob.v1.models.requests.BenfordAnalyzeV1Request
import com.danielssonjacob.v1.models.responses.BenfordAnalyzeV1Response

interface BenfordV1Service {

    fun analyze(request: BenfordAnalyzeV1Request): BenfordAnalyzeV1Response
}
