package ru.emi.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.Application
import io.ktor.server.application.log
import org.jetbrains.exposed.v1.core.DatabaseConfig
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.transactions.TransactionManager

fun Application.connectToDatabase(): HikariDataSource {
    val url = environment.config.property("postgres.url").getString()
    val user = environment.config.property("postgres.user").getString()
    val pass = environment.config.property("postgres.password").getString()
    val driverClassName = environment.config.property("postgres.driverClassName").getString()
    log.info("Configuring Hikari connection pool")
    val dataSource = provideDataSource(
        url = url,
        user = user,
        pass = pass,
        driverClass = driverClassName,
    )
    log.info("Connecting to postgres database at {}", url)
    val db = Database.connect(
        datasource = dataSource,
        databaseConfig = DatabaseConfig { defaultMaxAttempts = 1 },
    )
    TransactionManager.defaultDatabase = db
    return dataSource
}

private fun provideDataSource(
    url: String,
    user: String,
    pass: String,
    driverClass: String,
): HikariDataSource {
    val hikariConfig =
        HikariConfig().apply {
            jdbcUrl = url
            driverClassName = driverClass
            username = user
            password = pass
            maximumPoolSize = 20
            minimumIdle = 5
            isAutoCommit = false
            isReadOnly = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            idleTimeout = 60_000
            validate()
        }
    return HikariDataSource(hikariConfig)
}