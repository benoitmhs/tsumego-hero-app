package com.mrsanglier.tsumegohero.localdatasources

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.mrsanglier.tsumegohero.localdatasources.datastore.DataStoreFileName
import com.mrsanglier.tsumegohero.localdatasources.datastore.createDataStore
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
fun createDataStoreIOS(): DataStore<Preferences> {
    return createDataStore {
        val directory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        requireNotNull(directory).path + "/$DataStoreFileName"
    }
}