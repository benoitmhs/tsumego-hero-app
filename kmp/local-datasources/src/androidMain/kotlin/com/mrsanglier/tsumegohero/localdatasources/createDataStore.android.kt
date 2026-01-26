package com.mrsanglier.tsumegohero.localdatasources

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.mrsanglier.tsumegohero.localdatasources.datastore.DataStoreFileName
import com.mrsanglier.tsumegohero.localdatasources.datastore.createDataStore

fun createDataStoreAndroid(context: Context): DataStore<Preferences> {
    return createDataStore {
        context.filesDir.resolve(DataStoreFileName).absolutePath
    }
}
