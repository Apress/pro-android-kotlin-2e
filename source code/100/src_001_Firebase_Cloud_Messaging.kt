class MyFirebaseInstanceIdService : 
      FirebaseInstanceIdService() {
  override 
  fun onTokenRefresh() {
      // Get updated InstanceID token.
      val refreshedToken = 
          FirebaseInstanceId.getInstance().token
      Log.d(TAG, "Refreshed token: " + 
          refreshedToken!!)
        
      // If you want to send messages to this 
      // application instance or manage this apps 
      // subscriptions on the server side, send the
      // Instance ID token to your app server.
      sendRegistrationToServer(refreshedToken)  
  }
}
