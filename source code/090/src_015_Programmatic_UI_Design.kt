import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

class MyTopBar(private val nav:NavHostController, 
      private val activity:Activity) {
    // A boolean variable to store the display menu state
    private lateinit var displayMenu:MutableState<Boolean>

    @Composable
    fun make() {
        displayMenu = remember { mutableStateOf(false) }

        // fetching local context
        val context = LocalContext.current

        TopAppBar(
            title = { Text("My App") },
            actions = {
                // Icon button for dropdown menu
                IconButton(onClick = { displayMenu.value = 
                      !displayMenu.value }) {
                    Icon(Icons.Default.MoreVert, "")
                }

                DropdownMenu(
                    expanded = displayMenu.value,
                    onDismissRequest = { 
                        displayMenu.value = false }
                ) {

                    DropdownMenuItem(onClick = {
                        displayMenu.value = false
                        nav.navigate("settings")
                    }) {
                        Text(text = "Settings")
                    }

                    ... more items
                }
            }
        )
    }
}
