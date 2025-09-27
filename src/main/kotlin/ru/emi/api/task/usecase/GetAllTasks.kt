package ru.emi.api.task.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import ru.emi.api.task.model.Task
import ru.emi.api.task.table.Tasks
import ru.emi.api.task.table.toTask

suspend fun getAllTasks(): List<Task> =
    withContext(Dispatchers.IO) {
        transaction {
            Tasks
                .selectAll()
                .orderBy(Tasks.name)
                .map { it.toTask() }
        }
    }