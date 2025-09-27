package ru.emi.api.task.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.update
import ru.emi.api.task.model.TaskForm
import ru.emi.api.task.table.Tasks
import java.time.OffsetDateTime

suspend fun updateTask(
    id: Long,
    form: TaskForm,
) = withContext(Dispatchers.IO) {
    transaction {
        Tasks.update({ Tasks.id eq id }) {
            it[Tasks.description] = form.description
            it[Tasks.cron] = form.cron
            it[Tasks.enabled] = form.enabled
            it[Tasks.modifiedAt] = OffsetDateTime.now()
        }
    }
}