package ru.emi.common.exception

open class LightweightException(
    override val message: String,
) : RuntimeException(message, null, false, false)