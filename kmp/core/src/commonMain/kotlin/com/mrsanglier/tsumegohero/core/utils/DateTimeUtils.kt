package com.mrsanglier.tsumegohero.core.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun now(): Instant = Clock.System.now()

fun Instant.toDateTime(): LocalDateTime = this.toLocalDateTime(TimeZone.currentSystemDefault())