import androidx.activity.result.contract.
    ActivityResultContracts
...

class MainActivity : AppCompatActivity() {

    private lateinit var requestPermissionLauncher:
        ActivityResultLauncher<String>
    ....

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ...
        requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission is granted. Continue the 
                    // workflow in your app.
                    ...
                } else {
                    // The features requires a permission 
                    // that the user has denied. Explain to
                    // the user that the feature is 
                    // unavailable.
                    ...
                }
            }
    }
}
