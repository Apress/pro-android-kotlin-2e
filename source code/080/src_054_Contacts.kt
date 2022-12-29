package book.andrkotlpro.mycontacts1.db

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.provider.ContactsContract

data class MyContact(val id:String, val lookupKey,
                     val display:String, val email:String)

class MyContactsDataSource(
        private val contentResolver: ContentResolver) {
    fun fetchContactsPrimary(): List<MyContact> {
        val result: MutableList<MyContact> = 
            mutableListOf()

        val CONTENT_URI =
            ContactsContract.Contacts.CONTENT_URI

        val PROJECTION = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)

        val SORT_ORDER = ContactsContract.Contacts.
            DISPLAY_NAME_PRIMARY

        val cursor = contentResolver.query(
            CONTENT_URI,
            PROJECTION,
            null,
            null,
            SORT_ORDER
        )
        cursor?.let {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                result.add(
                    MyContact(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        ""
                    )
                )
                cursor.moveToNext()
            }
            cursor.close()
        }
        return result.toList()
    }

    fun fetchContactsData(): List<MyContact> {
        val result: MutableList<MyContact> = 
            mutableListOf()

        val CONTENT_URI =
            ContactsContract.Data.CONTENT_URI

        val PROJECTION = arrayOf(
            ContactsContract.Data._ID,
            ContactsContract.Data.LOOKUP_KEY,
            ContactsContract.Data.DISPLAY_NAME_PRIMARY,
            ContactsContract.CommonDataKinds.Email.ADDRESS)

        val SELECTION =
            ContactsContract.Data.MIMETYPE + " = '" +
            ContactsContract.CommonDataKinds.Email.
                CONTENT_ITEM_TYPE +
            "'"

        val SORT_ORDER = ContactsContract.Data.
            DISPLAY_NAME_PRIMARY

        val cursor = contentResolver.query(
            CONTENT_URI,
            PROJECTION,
            SELECTION,
            null,
            SORT_ORDER
        )
        cursor?.let {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                result.add(
                    MyContact(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                    )
                )
                cursor.moveToNext()
            }
            cursor.close()
        }
        return result.toList()
    }
}
