package com.example.myapp.vm

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

import com.example.myapp.vm.prefs.Prefs

class SettingsViewModel(application: Application): 
      AndroidViewModel(application) {
    private val dataStore = Prefs(application)

    var user by mutableStateOf("")
    var flag by mutableStateOf(false)
    var count by mutableStateOf(0)
    init {
        viewModelScope.launch {
           user = dataStore.loadString("app_user","")
                .first()
           launch { snapshotFlow { user }.onEach { 
              dataStore.save("app_user", it) }.collect() }
           flag = dataStore.loadBoolean("app_flag",false)
              .first()
           launch { snapshotFlow { flag }.onEach { 
              dataStore.save("app_flag", it) }.collect() }
           count = dataStore.loadInt("app_count","")
              .first()
           launch { snapshotFlow { count }.onEach { 
              dataStore.save("app_count", it) }.collect() }

        }
    }
}
