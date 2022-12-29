val contactUri = ContactsContract.Contacts.getLookupUri(
      id.toLong(), lookup)
quickBadge.assignContactUri(contactUri)
val thumbnail = 
      loadContactPhotoThumbnail(photo.toString())
quickBadge.setImageBitmap(thumbnail)
