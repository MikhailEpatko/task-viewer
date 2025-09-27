package ru.emi.api.task.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import ru.emi.api.task.model.TaskWithLogs
import ru.emi.api.task.table.Tasks
import ru.emi.api.task.table.toTaskWithLog
import ru.emi.api.tasklog.usecase.getLogsByTaskId

suspend fun getTaskWithLogsById(taskId: Long): TaskWithLogs? =
    withContext(Dispatchers.IO) {
        val logs = getLogsByTaskId(taskId)
        transaction {
            Tasks
                .selectAll()
                .where { Tasks.id eq taskId }
                .map { it.toTaskWithLog(logs) }
                .firstOrNull()
        }
    }