package com.mrsanglier.tsumegohero.core.utils

fun Int.minDigitNumber(digitNumber: Int): String =
    this.toString().padStart(digitNumber, '0')