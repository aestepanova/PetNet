package model


class Repository(private val db: ContentDB, private val newsNetworkProvider: NewsProvider)  {

    suspend fun registerUser(login: String, email: String, password: String){
        newsNetworkProvider.registerUser(login, email, password)
    }
    suspend fun loginUser(login: String, password: String): String? {
        return newsNetworkProvider.loginUser(login, password)
    }
    fun logOut(){
        newsNetworkProvider.logOut()
    }

    fun getLogin(): String{
        return newsNetworkProvider.getLogin()
    }
    fun getEmail(): String{
        return newsNetworkProvider.getEmail()
    }

    suspend fun insert(content: Content) {
        db.getDao().insert(content)
    }
    suspend fun checkToken(token: String): Boolean{
        return newsNetworkProvider.checkToken(token)
    }
    suspend fun deleteOneItem(id: Int) = db.getDao().deleteOneItem(id)
    suspend fun deleteAllItem() = db.getDao().deleteAllItem()

    fun existsItem(id: Int) = db.getDao().existsItem(id)

}