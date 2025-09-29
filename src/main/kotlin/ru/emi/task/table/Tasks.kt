package ru.emi.task.table

import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.dao.id.LongIdTable
import org.jetbrains.exposed.v1.datetime.timestampWithTimeZone
import ru.emi.task.model.Task
import ru.emi.task.model.TaskWithLogs
import ru.emi.tasklog.model.TaskLog

object Tasks : LongIdTable("task") {
    val name = text("name")
    val description = text("description")
    val cron = text("cron")
    val activityStatus = text("activity_status")
    val lastExecutionStatus = text("last_execution_status")
    val enabled = bool("enabled")
    val createdAt = timestampWithTimeZone("created_at")
    val modifiedAt = timestampWithTimeZone("modified_at")
}

fun ResultRow.toTask() =
    Task(
        id = get(Tasks.id).value,
        name = get(Tasks.name),
        description = get(Tasks.description),
        cron = get(Tasks.cron),
        activityStatus = get(Tasks.activityStatus),
        lastExecutionStatus = get(Tasks.lastExecutionStatus),
        enabled = get(Tasks.enabled),
        createdAt = get(Tasks.createdAt),
        modifiedAt = get(Tasks.modifiedAt),
    )

fun ResultRow.toTaskWithLog(logs: List<TaskLog>) =
    TaskWithLogs(
        id = get(Tasks.id).value,
        name = get(Tasks.name),
        description = get(Tasks.description),
        cron = get(Tasks.cron),
        activityStatus = get(Tasks.activityStatus),
        lastExecutionStatus = get(Tasks.lastExecutionStatus),
        enabled = get(Tasks.enabled),
        createdAt = get(Tasks.createdAt),
        modifiedAt = get(Tasks.modifiedAt),
        logs = logs,
    )