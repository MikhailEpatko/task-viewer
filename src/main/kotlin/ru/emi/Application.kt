package ru.emi

import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import ru.emi.db.connectToDatabase
import ru.emi.db.runLiquibase
import ru.emi.errors.configureErrorHandling
import ru.emi.metrics.configureMetrics
import ru.emi.task.routing.configureTaskTemplatesRouting
import ru.emi.thymeleaf.configureFreemarker

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    val dataSource = connectToDatabase()
    runLiquibase(dataSource)

    configureErrorHandling()
    configureFreemarker()
    configureTaskTemplatesRouting()
    configureMetrics()
}