package ru.emi.task.model

data class TaskModel(
    val id: Long,
    val name: String,
    val description: String,
    val cron: String,
    val activityStatus: String,
    val lastExecutionStatus: String,
    val enabled: Boolean,
    val createdAt: String,
    val modifiedAt: String,
)