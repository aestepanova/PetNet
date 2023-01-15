package model


import android.content.Context
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.example.petnet.auth.AuthenticationViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun buildDatabase(context: Context) =
    Room.databaseBuilder(
        context.applicationContext,
        ContentDB::class.java,
        "content_db.db"
    ).build()

fun provideConentDao(db: ContentDB): ContentDao {
    return db.getDao()
}

val appModule = module{
    single{androidContext()}
    single { buildDatabase(androidApplication())}
    single{PreferenceManager.getDefaultSharedPreferences(androidApplication())}
    single{Repository(get(), get())}
    single { AuthenticationViewModel(get(),get())}
}