import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("io.ktor.plugin")
    kotlin("plugin.serialization")
    id("org.jlleitschuh.gradle.ktlint")
}

group = "ru.emi"
java.sourceCompatibility = JavaVersion.VERSION_21

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
//    ktor
    implementation(Ktor.server.core)
    implementation(Ktor.server.contentNegotiation)
    implementation(Ktor.server.netty)
    implementation("io.ktor:ktor-server-config-yaml:_")
    implementation(Ktor.plugins.serialization.kotlinx.json)
    implementation(Ktor.server.statusPages)
    implementation(Ktor.server.freeMarker)

//    authentication
    implementation(Ktor.server.auth.jwt)
    implementation("io.ktor:ktor-server-auth-jwt-jvm:_")

//    http client
    implementation(Ktor.client.core)
    implementation(Ktor.client.okHttp)
    implementation(Ktor.client.logging)
    implementation(Ktor.client.contentNegotiation)

//    database
    implementation(JetBrains.exposed.core)
    implementation(JetBrains.exposed.jdbc)
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:_")
    // implementation("org.jetbrains.exposed:exposed-json:_")
    implementation(KotlinX.datetime)
    implementation("org.postgresql:postgresql:_")
    implementation("com.zaxxer:HikariCP:_")
    implementation("org.liquibase:liquibase-core:_") {
        exclude(module = "commons-lang3")
    }
    implementation("org.apache.commons:commons-lang3:_")

//    vault
    implementation("com.bettercloud:vault-java-driver:_")

//    logging
    implementation("io.github.oshai:kotlin-logging-jvm:_")
    implementation("org.slf4j:slf4j-api:_")
    implementation("ch.qos.logback:logback-classic:_")
    implementation("net.logstash.logback:logstash-logback-encoder:_")
    implementation("org.codehaus.janino:janino:_")

//    metrics
    implementation(Ktor.server.metricsMicrometer)
    implementation("io.micrometer:micrometer-registry-prometheus:_")

//    swagger
    implementation("io.ktor:ktor-server-swagger:_")

//    testing
    testImplementation(Ktor.server.testHost)
    testImplementation(Kotlin.test)
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.set(listOf("-Xjsr305=strict"))
        jvmTarget.set(JvmTarget.JVM_21)
    }
}