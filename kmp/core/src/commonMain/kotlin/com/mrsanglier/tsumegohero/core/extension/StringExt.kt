package com.mrsanglier.tsumegohero.core.extension

fun String.removeSpace(): String =
    this.replace(" ", "")

val SpecialCharRegex = Regex("[^a-zA-Z0-9\\s]")
val NumberRegex = Regex("\\d")
val UppercaseRegex = Regex("[A-Z]")