package ru.emi.tasklog.table

import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.dao.id.LongIdTable
import org.jetbrains.exposed.v1.datetime.timestampWithTimeZone
import ru.emi.tasklog.model.TaskLog

object TaskLogs : LongIdTable("task_log") {
    val taskId = long("task_id")
    val executionStatus = text("execution_status")
    val error = text("error")
    val startedAt = timestampWithTimeZone("started_at")
    val finishedAt = timestampWithTimeZone("finished_at")
}

fun ResultRow.toTaskLog() =
    TaskLog(
        id = get(TaskLogs.id).value,
        taskId = get(TaskLogs.taskId),
        executionStatus = get(TaskLogs.executionStatus),
        error = get(TaskLogs.error),
        startedAt = get(TaskLogs.startedAt),
        finishedAt = get(TaskLogs.finishedAt),
    )