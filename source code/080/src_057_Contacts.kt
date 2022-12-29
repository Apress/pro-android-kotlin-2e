package book.andrkotlpro.mycontacts1.db

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MyContactsRepository(
        private val source: MyContactsDataSource,
        private val myDispatcher: CoroutineDispatcher) {
    suspend fun fetchContacts(): List<MyContact> {
        return withContext(myDispatcher) {
            source.fetchContactsData()
        }
    }
}
