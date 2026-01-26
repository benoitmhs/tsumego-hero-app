package com.mrsanglier.tsumegohero.core.extension

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull

fun JsonObject.optString(key: String): String? = get(key)?.jsonPrimitive?.contentOrNull
fun JsonObject.optInt(key: String): Int? = get(key)?.jsonPrimitive?.intOrNull
fun JsonObject.optDouble(key: String): Double? = get(key)?.jsonPrimitive?.doubleOrNull
fun JsonObject.optLong(key: String): Long? = get(key)?.jsonPrimitive?.longOrNull
fun JsonObject.optJson(key: String): JsonObject? = get(key)?.jsonObject