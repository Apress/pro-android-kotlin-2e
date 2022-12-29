  dependencies {
    ...
    implementation "androidx.wear:wear:1.2.0"

    // Add support for wearable specific inputs
    implementation "androidx.wear:wear-input:1.1.0"
    implementation "androidx.wear:wear-input-testing:1.1.0"

    // Use to implement wear ongoing activities
    implementation "androidx.wear:wear-ongoing:1.0.0"

    // Use to implement support for interactions from the 
    // Wearables to Phones
    implementation "androidx.wear:" +
        "wear-phone-interactions:1.1.0-alpha03"
    // Use to implement support for interactions between 
    // the Wearables and Phones
    implementation "androidx.wear:" +
        "wear-remote-interactions:1.0.0"
  }
