package ru.emi.task.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import ru.emi.task.model.Task
import ru.emi.task.table.Tasks
import ru.emi.task.table.toTask

suspend fun getAllTasks(): List<Task> =
    withContext(Dispatchers.IO) {
        transaction {
            Tasks
                .selectAll()
                .orderBy(Tasks.name)
                .map { it.toTask() }
        }
    }