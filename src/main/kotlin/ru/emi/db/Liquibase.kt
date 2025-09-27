package ru.emi.db

import com.zaxxer.hikari.HikariDataSource
import liquibase.Contexts
import liquibase.LabelExpression
import liquibase.Liquibase
import liquibase.Scope
import liquibase.SingletonScopeManager
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor

private const val CHANGELOG = "db/changelog/master.yml"

fun runLiquibase(ds: HikariDataSource) {
    val conn = ds.connection
    val database =
        DatabaseFactory
            .getInstance()
            .findCorrectDatabaseImplementation(JdbcConnection(conn))
    Scope.setScopeManager(SingletonScopeManager())
    val liquibase =
        Liquibase(
            CHANGELOG,
            ClassLoaderResourceAccessor(),
            database,
        )
    liquibase.update(Contexts(), LabelExpression())
}