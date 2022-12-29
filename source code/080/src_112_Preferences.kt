package com.example.myapp

import android.content.Context
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.myapp.ui.*
import com.example.myapp.vm.SettingsViewModel

// Add a preferences DataStore to the Context. The 
// documentation suggests to do it that way, so we have
// a central single access location.
val Context.settingsDataStore by 
    preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {
    ...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ...

        // view models ------------------------------------
        ...
        // Obtain a ViewModel for the settings. Because we
        // need a context object inside the ViewModel, we
        // must use a ViewModelProvider as shown here
        val settingsViewModel: SettingsViewModel by 
        viewModels {
            ViewModelProvider.AndroidViewModelFactory.
            getInstance(application)
        }
        // ------------------------------------------------

        setContent {
          // We are now inside JetPack Compose...
          // First a navigation controller. We use
          // rememberNavController(), so Compose
          // knows about the navigation controller
          // and appropriately handles navigation
          // state changes.
          val navController = rememberNavController()
          ...

          Scaffold(
            topBar = { 
              // Indicates whether or not the dropdown
              // menu is shown. Because of remember() the
              // value prevails for recomposition, and
              // because of mutableStateOf() Compose can
              // dynamically react on state changes: 
              val displayMenu = remember { 
                    mutableStateOf(false) }
              TopAppBar(
                title = { Text(...) },
                actions = {
                  // Icon button for dropdown menu
                  IconButton(onClick = { 
                      displayMenu.value = 
                            !displayMenu.value }) {
                    Icon(Icons.Default.MoreVert, "")
                  }
                  DropdownMenu(
                    expanded = displayMenu.value,
                    onDismissRequest = { 
                          displayMenu.value = false }
                  ) {
                    // A dropdown menu item to navigate
                    // to the settings screen:
                    DropdownMenuItem(onClick = {
                      displayMenu.value = false
                      nav.navigate("settings")
                    }) {
                      Text(text = "Settings")
                    }
                    ...
                  }
                }
              )
            },
            content = {
              // The content area. We let the navigation
              // controller decide what to show here.
              NavHost(navController = navController, 
                    startDestination = "home") {
                composable("home"){
                  // The contents of the "home" screen
                  ...
                }
                ...
                composable("settings"){
                  // The settings screen
                  SettingsScreen(settingsViewModel).make()
                }
              }
            }
          )
        }
    }
    ...
}
