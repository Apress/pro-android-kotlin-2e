import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.lifecycle.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // view models ------------------------------------
        val homeViewModel: HomeViewModel by viewModels()
        ...
        // ------------------------------------------------

        setContent {
          val navController = rememberNavController()
          val topBar by remember { mutableStateOf(
              MyTopBar(navController, this)) }
          val startDestination = "home"

          Scaffold(
            topBar = { topBar.make() },
            content = {
              NavHost(navController = navController, 
                    startDestination = startDestination) {
                composable("home"){
                    HomeScreen(homeViewModel, 
                        navController).make()
                }
                composable("otherScreen"){
                    ...
                }
                ...
              }
            }
          )
        }
    }
}
