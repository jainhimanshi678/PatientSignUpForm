package com.sk.patientsignupform.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take

/**
 * Datastore.
 */
private val Context.dataStore by preferencesDataStore("patientDataStore")

/**
 * Datastore pref util class.
 */
class PrefUtil(private val context: Context) {

    val dataStore: DataStore<Preferences> = context.dataStore

    /**
     * Saves string data.
     */
    suspend fun saveStringData(
        key: Preferences.Key<String>,
        data: String,
        listener: (Boolean) -> Unit
    ) {
        dataStore.edit { preference ->
            preference[key] = data
            listener(true)
        }
    }

    /**
     * Gets string data.
     */
    suspend fun getStringData(key: Preferences.Key<String>, listener: (String) -> Unit) {
        val userDataFlow: Flow<String> = dataStore.data.map {
            it[key] ?: ""
        }
        userDataFlow.take(1).collect {
            listener(it)
        }
    }

    /**
     * Puts boolean data.
     */
    suspend fun putBoolean(
        key: Preferences.Key<Boolean>,
        data: Boolean,
        listener: (Boolean) -> Unit
    ) {
        dataStore.edit { preference ->
            preference[key] = data
            listener(true)
        }
    }

    /**
     * Gets boolean data.
     */
    suspend fun getBooleanData(
        key: Preferences.Key<Boolean>,
        listener: (Boolean) -> Unit,
        defaultValue: Boolean = false
    ) {
        val userDataFlow: Flow<Boolean> = dataStore.data.map {
            it[key] ?: defaultValue
        }
        userDataFlow.take(1).collect {
            listener(it)
        }
    }
}

