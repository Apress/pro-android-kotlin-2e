class MyViewModel(): ViewModel() {
    fun doSomething() {
        // Create a coroutine to move the execution off 
        // the UI thread
        viewModelScope.launch(Dispatchers.IO) {
            // do some background work...
        }
    }
}
