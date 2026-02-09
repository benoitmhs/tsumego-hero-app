package com.mrsanglier.tsumegohero.core.extension

fun Int.circular(size: Int): Int =
    ((this % size) + size) % size