import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var text1 by mutableStateOf("Hello")
    var text2 by mutableStateOf("")
}
