
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.*
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.
    stringPreferencesKey
import kotlinx.coroutines.runBlocking

// A central "singleton" DataStore. You maybe put this 
// alongside (not inside) the main Activity.
val Context.settingsDataStore by 
    preferencesDataStore(name = "settings")

// A wrapper for using the settings datastore
class Prefs(val context: Context) {
    suspend fun <T> save(key:String, v: T) {
        context.settingsDataStore.edit { preferences ->
            when(v) {
                is String -> preferences[
                    stringPreferencesKey(key)] = 
                        v as String
                else -> throw IllegalArgumentException(
                    v!!::class.java.toString())
            }
        }
    }

    fun loadString(key:String, defVal:String = "") 
          : Flow<String> {
        return context.settingsDataStore.data
            .map { preferences ->
                preferences[stringPreferencesKey(key)] 
                    ?: defVal
            }
    }

    // Non-coroutines getter. For use outside of Coroutines
    fun getString(key:String, defVal:String = "") = 
        runBlocking { loadString(key, defVal).first() }
}

// A ViewModel. You use this in client code to read
// (and write) preferences.
class SettingsViewModel(application: Application): 
      AndroidViewModel(application) {
    private val dataStore = Prefs(application)

    // Because of mutableStateOf(""), this is an observed
    // variable
    var someString by mutableStateOf("")
    
    init {
        viewModelScope.launch {
            // async loading from the datastore
            someString = dataStore.loadString(
                "some_string","").first()
            // automatically save changes to the datastore
            launch { snapshotFlow { someString }.onEach { 
                dataStore.save("some_string", it) }.
                    collect() }
        }
    }

}
