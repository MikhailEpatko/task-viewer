package ru.emi.task.model

import ru.emi.tasklog.model.TaskLog
import ru.emi.extensions.toLocalString
import java.time.OffsetDateTime

data class TaskWithLogs(
    val id: Long,
    val name: String,
    val description: String,
    val cron: String,
    val activityStatus: String,
    val lastExecutionStatus: String?,
    val enabled: Boolean,
    val createdAt: OffsetDateTime,
    val modifiedAt: OffsetDateTime,
    val logs: List<TaskLog>,
) {
    fun toModel() =
        TaskWithLogsModel(
            id = id,
            name = name,
            description = description,
            cron = cron,
            activityStatus = activityStatus,
            lastExecutionStatus = lastExecutionStatus ?: "",
            enabled = enabled,
            createdAt = createdAt.toLocalString(),
            modifiedAt = modifiedAt.toLocalString(),
            logs = logs.map { it.toModel() },
        )
}