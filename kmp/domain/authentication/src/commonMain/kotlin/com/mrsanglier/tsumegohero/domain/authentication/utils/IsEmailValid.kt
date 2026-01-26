package com.mrsanglier.tsumegohero.domain.authentication.utils

internal fun isEmailFormatValid(email: String): Boolean =
    email.matches(EmailRegex.toRegex())

private const val EmailRegex =
    "^[a-zA-Z0-9+._%-]{1,256}@[a-zA-Z0-9-]{1,64}(?:\\.[a-zA-Z0-9-]{1,25})*$"