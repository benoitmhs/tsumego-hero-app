package com.mrsanglier.tsumegohero.coreui.utils

//import androidx.core.bundle.Bundle

//inline fun <reified T : Any?> serializableType(
//    json: Json = Json,
//) = object : NavType<T?>(isNullableAllowed = null is T) {
//    override fun get(bundle: Bundle, key: String) =
//        bundle.getString(key)?.let<String, T?>(json::decodeFromString)
//
//    override fun parseValue(value: String): T? = if (value == "null") {
//        null
//    } else {
//        json.decodeFromString(value)
//    }
//
//    override fun serializeAsValue(value: T?): String =
//        value?.let { json.encodeToString(value) } ?: "null"
//
//    override fun put(bundle: Bundle, key: String, value: T?) {
//        if (value != null) {
//            bundle.putString(key, json.encodeToString(value))
//        } else {
//            bundle.putString(key, null)
//        }
//    }
//}
