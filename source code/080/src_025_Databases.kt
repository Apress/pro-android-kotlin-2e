@Dao
interface ContactDao {
    @Insert
    suspend fun insert(vararg contacts: Contact)

    @Query("SELECT * FROM Contact WHERE uid = :uId")
    suspend fun findById(uId: Int): List<Contact>

    @Query("SELECT * FROM Contact WHERE" +
      " employeeName like = '%' || :name || '%'")
    fun loadByEmployeeNameLike(name: String): 
    Flow<List<Contact>>
}
