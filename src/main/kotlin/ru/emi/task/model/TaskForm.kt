package ru.emi.task.model

data class TaskForm(
    val name: String = "",
    val description: String = "",
    val cron: String = "",
    val enabled: Boolean = false,
)