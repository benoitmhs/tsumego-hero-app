package com.mrsanglier.tsumegohero.core.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.Instant

fun now(): Instant = Clock.System.now()

fun Instant.toDateTime(): LocalDateTime = this.toLocalDateTime(TimeZone.currentSystemDefault())