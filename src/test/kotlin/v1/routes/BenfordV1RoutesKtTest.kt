package v1.routes

import com.danielssonjacob.configureRouting
import com.danielssonjacob.configureSerialization
import com.danielssonjacob.v1.models.requests.BenfordAnalyzeV1Request
import com.danielssonjacob.v1.models.responses.BenfordAnalyzeV1Response
import com.danielssonjacob.v1.services.BenfordV1Service
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.json.Json
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton

class BenfordV1RoutesKtTest {

    private val benfordV1ServiceMock = mockk<BenfordV1Service>()

    fun TestApplicationBuilder.setupApp() {
        application {
            di { bind<BenfordV1Service>() with singleton { benfordV1ServiceMock } }
            configureSerialization()
            configureRouting()
        }
    }

    @Test
    fun `should return 200 OK`() = testApplication {
        setupApp()
        val mockResponse =
            BenfordAnalyzeV1Response(
                expectedDistribution = mapOf(),
                actualDistribution = mapOf(),
                chiSquareValue = 1.23,
                isSignificant = false,
            )
        every { benfordV1ServiceMock.analyze(any()) } returns mockResponse

        val request = BenfordAnalyzeV1Request(text = "1 2 3 4 5 6 7 8 9", significanceLevel = 0.05)

        client
            .post("/v1/distribution/benford/analyze") {
                contentType(ContentType.Application.Json)
                setBody(Json.encodeToString(request))
            }
            .apply { assertEquals(HttpStatusCode.OK, status) }
    }

    @Test
    fun `should return 400 Bad Request, significanceLevel out of range`() = testApplication {
        setupApp()
        client
            .post("/v1/distribution/benford/analyze") {
                contentType(ContentType.Application.Json)
                setBody(
                    """{
                        "text":"1 2 3 4 5 6 7 8 9", 
                        "significanceLevel":1.5
                    }"""
                )
            }
            .apply { assertEquals(HttpStatusCode.BadRequest, status) }
    }
}
