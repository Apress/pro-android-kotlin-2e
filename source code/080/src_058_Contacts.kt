package book.andrkotlpro.mycontacts1

import android.app.Application
import androidx.lifecycle.*
import book.andrkotlpro.mycontacts1.db.*
import kotlinx.coroutines.Dispatchers

class ContactsViewModel (
    context: Application,
    private val myContactsRepository: MyContactsRepository
) : AndroidViewModel(context) {

    var myContacts: LiveData<List<MyContact>> = liveData {
        emit(myContactsRepository.fetchContacts())
    }
}

class ContactsViewModelFactory(
        private val application: Application) 
        : ViewModelProvider.AndroidViewModelFactory(
              application) {
    override fun <T : ViewModel?> 
    create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(
                ContactsViewModel::class.java)) {
            val source = MyContactsDataSource(
                application.contentResolver)
            ContactsViewModel(application, 
                MyContactsRepository(source, 
                    Dispatchers.IO)) as T
        } else
            throw IllegalArgumentException(
                "Unknown ViewModel class")
    }
} 
