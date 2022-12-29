import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.navigation.NavHostController

class HomeScreen(
    private val vm: HomeViewModel,
    private val navController: NavHostController
) {

    @Composable
    fun make() {
        Column {
            Text(text = vm.text1)
            TextField(value = vm.text2, 
                onValueChange = {
                vm.text2 = it } )
        }
    }
}
