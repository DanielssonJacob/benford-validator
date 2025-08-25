plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.spotless)
}

kotlin {
    jvmToolchain(21)
}

group = "com.danielssonjacob"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)


    //logging:
    implementation(libs.ktor.server.call.logging)

    //dependency injection
    implementation(libs.kodein.di)


    //apache math
    implementation(libs.apache.math)

    //test
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.mockk)

}

spotless {
    kotlin {
        ktfmt().kotlinlangStyle()

    }
}

