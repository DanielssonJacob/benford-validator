package com.danielssonjacob

import io.ktor.server.testing.*
import kotlin.test.Test

class ApplicationTest {

    @Test fun `Should start`() = testApplication { application { module() } }
}
