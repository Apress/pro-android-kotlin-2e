val SELECTION = 
    ContactsContract.CommonDataKinds.Email.ADDRESS +
    " LIKE ? " + "AND " +
    ContactsContract.Data.MIMETYPE + " = '" + 
    ContactsContract.
          CommonDataKinds.Email.CONTENT_ITEM_TYPE +  
    "'"
val selectionArgs = arrayOf("%" + search + "%")
...
val cursor = contentResolver.query(
        CONTENT_URI,
        PROJECTION,
        SELECTION,
        selectionArgs,
        SORT_ORDER
)
