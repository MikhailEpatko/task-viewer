package ru.emi.api.task.model

import ru.emi.api.tasklog.model.TaskLogModel

data class TaskWithLogsModel(
    val id: Long,
    val name: String,
    val description: String,
    val cron: String,
    val activityStatus: String,
    val lastExecutionStatus: String,
    val enabled: Boolean,
    val createdAt: String,
    val modifiedAt: String,
    val logs: List<TaskLogModel>,
)