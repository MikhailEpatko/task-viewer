package ru.emi

import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import ru.emi.api.metrics.configureMetrics
import ru.emi.api.task.routing.configureTaskTemplatesRouting
import ru.emi.common.configureAuthentication
import ru.emi.common.configureErrorHandling
import ru.emi.common.configureSerialization
import ru.emi.db.connectToDatabase
import ru.emi.db.runLiquibase
import ru.emi.thymeleaf.configureTemplating

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    val dataSource = connectToDatabase()
    runLiquibase(dataSource)

    configureAuthentication()
    configureSerialization()
    configureErrorHandling()
    configureTemplating()
    configureTaskTemplatesRouting()
    configureMetrics()
}