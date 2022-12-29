if(checkSelfPermission(Manifest.permission.CAMERA) !=
       PackageManager.PERMISSION_GRANTED) {
    if(shouldShowRequestPermissionRationale(
            Manifest.permission.CAMERA)) {
        // Explain to the user why your app requires 
        // this permission. The view should show
        // a button linked to
        //   requestPermissionLauncher.launch(
        //        Manifest.permission.CAMERA
        //   )
        ...
    } else {
        // Ask the system for the permission.
        requestPermissionLauncher.launch(
            Manifest.permission.CAMERA
        )
    }
} else {
    // Use the API that requires the permission.
    ....
}
