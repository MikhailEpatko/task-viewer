package ru.emi.api.tasklog.model

data class TaskLogModel(
    val id: Long,
    val executionStatus: String,
    val error: String,
    val startedAt: String,
    val finishedAt: String,
)