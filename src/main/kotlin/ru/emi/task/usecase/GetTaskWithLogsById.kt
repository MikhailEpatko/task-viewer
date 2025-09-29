package ru.emi.task.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import ru.emi.task.model.TaskWithLogs
import ru.emi.task.table.Tasks
import ru.emi.task.table.toTaskWithLog
import ru.emi.tasklog.usecase.getLogsByTaskId

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