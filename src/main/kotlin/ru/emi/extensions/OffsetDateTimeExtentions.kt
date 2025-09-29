package ru.emi.extensions

import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

val MSC_ZONE: ZoneId = ZoneId.of("Europe/Moscow")
val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")

fun OffsetDateTime.toLocalString(): String = formatter.format(atZoneSameInstant(MSC_ZONE))