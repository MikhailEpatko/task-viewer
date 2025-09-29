package ru.emi.task.routing

import io.ktor.server.application.Application
import io.ktor.server.freemarker.FreeMarkerContent
import io.ktor.server.http.content.staticResources
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import ru.emi.task.model.TaskForm
import ru.emi.task.usecase.getAllTasks
import ru.emi.task.usecase.getTaskById
import ru.emi.task.usecase.getTaskWithLogsById
import ru.emi.task.usecase.updateTask

fun Application.configureTaskTemplatesRouting() {
    routing {
        staticResources("/images", "static/images")
        route("/tasks") {
            // Получить все задачи
            get {
                call.respond(FreeMarkerContent("index.ftl", mapOf("tasks" to getAllTasks().map { it.toModel() })))
            }

            // Получить по ID задачу с логами
            get("/{id}") {
                val id = call.pathParameters["id"]?.toLong()
                when {
                    id == null ->
                        call.respondRedirect("/tasks")

                    else ->
                        getTaskWithLogsById(id)
                            ?.toModel()
                            ?.let { call.respond(FreeMarkerContent("task-details.ftl", mapOf("task" to it))) }
                            ?: call.respondRedirect("/tasks")
                }
            }

            // Перейти на форму редактирования задачи
            get("/{id}/edit") {
                val id = call.parameters["id"]?.toLongOrNull()
                if (id == null) {
                    call.respondRedirect("/tasks")
                    return@get
                }
                val task = getTaskById(id)?.toForm()
                if (task == null) {
                    call.respondRedirect("/tasks")
                    return@get
                }
                call.respond(
                    FreeMarkerContent(
                        "task-form.ftl",
                        mapOf(
                            "taskForm" to task,
                            "taskId" to id,
                            "isEdit" to true,
                        ),
                    ),
                )
            }

            // Редактирование задачи
            post("/{id}/edit") {
                val id = call.pathParameters["id"]?.toLong()
                when {
                    id == null ->
                        call.respondRedirect("/tasks")

                    else -> {
                        val form = call.receiveParameters()
                        val request = TaskForm(
                            name = form["name"] ?: "",
                            description = form["description"] ?: "",
                            cron = form["cron"] ?: "",
                            enabled = form["enabled"]?.toBoolean() ?: false,
                        )
                        updateTask(id, request)
                        call.respondRedirect("/tasks")
                    }
                }
            }
        }
    }
}