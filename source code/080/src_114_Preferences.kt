package com.example.myapp.prefs

import android.content.Context
import androidx.datastore.preferences.core.
    booleanPreferencesKey
import androidx.datastore.preferences.core.
    intPreferencesKey
import androidx.datastore.preferences.core.
    stringPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

import com.example.myapp.settingsDataStore

class Prefs(val context: Context) {
    suspend fun <T> save(key:String, v: T) {
        context.settingsDataStore.edit { preferences ->
            when(v) {
                is String -> preferences[
                    stringPreferencesKey(key)] = 
                        v as String
                is Boolean -> preferences[
                    booleanPreferencesKey(key)] = 
                        v as Boolean
                is Int -> preferences[
                    intPreferencesKey(key)] = 
                        v as Int
                else -> throw 
                  IllegalArgumentException(
                      v!!::class.java.toString())
            }
        }
    }

    fun loadBoolean(key:String, defVal:Boolean = false) 
          : Flow<Boolean> {
        return context.settingsDataStore.data
            .map { preferences ->
                preferences[
                    booleanPreferencesKey(key)] ?: defVal
            }
    }

    fun loadString(key:String, defVal:String = "") 
          : Flow<String> {
        return context.settingsDataStore.data
            .map { preferences ->
                preferences[
                    stringPreferencesKey(key)] ?: defVal
            }
    }

    fun loadInt(key:String, defVal:Int = 0) 
          : Flow<Int> {
        return context.settingsDataStore.data
            .map { preferences ->
                preferences[
                    intPreferencesKey(key)] ?: defVal
            }
    }

    // Non-coroutines getters
    fun getString(key:String, defVal:String = "") = 
        runBlocking { loadString(key, defVal).first() }
    fun getInt(key:String, defVal:Int = 0) = 
        runBlocking { loadInt(key, defVal).first() }
    fun getBoolean(key:String, defVal:Boolean = false) = 
        runBlocking { loadBoolean(key, defVal).first() }
}
