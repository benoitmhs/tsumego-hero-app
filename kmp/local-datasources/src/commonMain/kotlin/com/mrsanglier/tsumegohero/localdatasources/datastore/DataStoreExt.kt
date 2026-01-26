package com.mrsanglier.tsumegohero.localdatasources.datastore

import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences

fun <T> MutablePreferences.editOrDelete(key: Preferences.Key<T>, value: T?) {
    if (value == null) {
        remove(key)
    } else {
        this[key] = value
    }
}