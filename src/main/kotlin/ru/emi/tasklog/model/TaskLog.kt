package ru.emi.tasklog.model

import ru.emi.extensions.toLocalString
import java.time.OffsetDateTime

data class TaskLog(
    val id: Long,
    val taskId: Long,
    val executionStatus: String,
    val error: String?,
    val startedAt: OffsetDateTime,
    val finishedAt: OffsetDateTime?,
) {
    fun toModel() =
        TaskLogModel(
            id = id,
            executionStatus = executionStatus,
            error = error ?: "",
            startedAt = startedAt.toLocalString(),
            finishedAt = finishedAt?.toLocalString() ?: "",
        )
}