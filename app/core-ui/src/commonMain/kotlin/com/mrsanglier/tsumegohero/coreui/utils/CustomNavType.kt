package com.mrsanglier.tsumegohero.coreui.utils

//import androidx.core.bundle.Bundle

//inline fun <reified T : Any> customNavType(): NavType<T> =
//    object : NavType<T>(isNullableAllowed = false) {
//        override fun get(bundle: Bundle, key: String): T? {
//            return Json.decodeFromString(bundle.getString(key) ?: return null)
//        }
//
//        override fun parseValue(value: String): T {
//            return Json.decodeFromString(UriCodec.decode(value))
//        }
//
//        override fun put(bundle: Bundle, key: String, value: T) {
//            bundle.putString(key, Json.encodeToString(value))
//        }
//
//        override fun serializeAsValue(value: T): String {
//            return UriCodec.encode(Json.encodeToString(value))
//        }
//    }
// Not work
//inline fun <reified T : Any?> customNullableNavType(): NavType<T> =
//    object : NavType<T>(isNullableAllowed = true) {
//        override fun get(bundle: Bundle, key: String): T? {
//            val jsonString = bundle.getString(key)
//            return if (jsonString.isNullOrEmpty()) null else Json.decodeFromString(jsonString)
//        }
//
//        override fun parseValue(value: String): T {
//            return Json.decodeFromString(UriCodec.decode(value))
//        }
//
//        override fun put(bundle: Bundle, key: String, value: T) {
//            bundle.putString(key, value?.let { Json.encodeToString(value) })
//        }
//
//        override fun serializeAsValue(value: T): String {
//            return if (value != null) {
//                UriCodec.encode(Json.encodeToString(value))
//            } else {
//                // Represent null explicitly, e.g., with "@null"
//                "@null"
//            }
//        }
//    }
