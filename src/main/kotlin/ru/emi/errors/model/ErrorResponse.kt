package ru.emi.errors.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val error: String,
    val key: String? = null,
)