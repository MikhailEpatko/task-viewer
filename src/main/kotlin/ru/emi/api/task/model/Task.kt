package ru.emi.api.task.model

import ru.emi.common.time.toLocalString
import java.time.OffsetDateTime

data class Task(
    val id: Long,
    val name: String,
    val description: String,
    val cron: String,
    val activityStatus: String,
    val lastExecutionStatus: String,
    val enabled: Boolean,
    val createdAt: OffsetDateTime,
    val modifiedAt: OffsetDateTime,
) {
    fun toModel() =
        TaskModel(
            id = id,
            name = name,
            description = description,
            cron = cron,
            activityStatus = activityStatus,
            lastExecutionStatus = lastExecutionStatus,
            enabled = enabled,
            createdAt = createdAt.toLocalString(),
            modifiedAt = modifiedAt.toLocalString(),
        )

    fun toForm() =
        TaskForm(
            name = name,
            description = description,
            cron = cron,
            enabled = enabled,
        )
}