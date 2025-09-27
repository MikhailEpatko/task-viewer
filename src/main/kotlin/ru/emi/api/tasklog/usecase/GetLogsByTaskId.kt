package ru.emi.api.tasklog.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.v1.core.SortOrder
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import ru.emi.api.tasklog.model.TaskLog
import ru.emi.api.tasklog.table.TaskLogs
import ru.emi.api.tasklog.table.toTaskLog

suspend fun getLogsByTaskId(taskId: Long): List<TaskLog> =
    withContext(Dispatchers.IO) {
        transaction {
            TaskLogs
                .selectAll()
                .where { TaskLogs.taskId eq taskId }
                .orderBy(TaskLogs.id, SortOrder.DESC)
                .limit(50)
                .map { it.toTaskLog() }
        }
    }