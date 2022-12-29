AndroidView(
    modifier = Modifier.width(60.dp).height(60.dp),
    factory = { context ->
        // This instantiates a non-Compose View:
        QuickContactBadge(context).apply {
            val contactUri = ContactsContract.
                Contacts.getLookupUri(
                    itm.id.toLong(),
                    itm.lookupKey)
            assignContactUri(contactUri)
            val thumbnail =
                loadContactPhotoThumbnail(
                    itm.photoThumbnailUri)
            setImageBitmap(thumbnail)
        }
    },
    update = { view ->
        // View's been inflated or state has been 
        // updated. Add logic here if necessary.
        // Because the QuickContactBadge does not
        // represent a state, we don't need to add
        // anything here. 
    }
)
