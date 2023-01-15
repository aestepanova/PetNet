package com.example.petnet.ui

import android.content.Context.*
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.preference.PreferenceManager
import com.example.petnet.auth.AuthenticationViewModel
import com.example.petnet.map.MapViewModel
import com.example.petnet.search.SearchViewModel
import org.koin.androidx.compose.get


@ExperimentalAnimationApi
class AuthenticationActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
        setContent {
            // A surface container using the 'background' color from the theme
                MyApp(prefs = prefs, aa = this )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MyApp(prefs: SharedPreferences, aa: AuthenticationActivity) {
    val viewmodel: AuthenticationViewModel = get()
    val userState by viewmodel.uiState.collectAsState()
    viewmodel.checkToken(prefs.getString("Token", "")!!)
    val mapViewModel = MapViewModel(get())
    val searchViewModel = SearchViewModel(get())
    Crossfade(targetState = userState.isauthenticate ) { state ->
        when(state){
            false -> {
                Crossfade(targetState = userState.isLoading ) { loadState ->
                    when (loadState) {
                        false -> {
                            AuthenticationScreen(hideKeyboard = {
                                val imm: InputMethodManager =
                                    aa.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                                //Find the currently focused view, so we can grab the correct window token from it.
                                var view = aa.currentFocus
                                //If no view currently has focus, create a new one, just so we can grab a window token from it
                                if (view == null) {
                                    view = View(aa)
                                }
                                imm.hideSoftInputFromWindow(view.windowToken, 0)
                            }, viewmodel)
                        }
                        true -> {
                            LoadingScreen()
                        }
                    }
                }
            }
            true -> {
                MainScreen(mapViewModel, searchViewModel, hideKeyboard= {
                    val imm: InputMethodManager =
                        aa.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    //Find the currently focused view, so we can grab the correct window token from it.
                    var view = aa.currentFocus
                    //If no view currently has focus, create a new one, just so we can grab a window token from it
                    if (view == null) {
                        view = View(aa)
                    }
                    imm.hideSoftInputFromWindow(view!!.windowToken, 0)
                },
                    viewmodel
                )
            }
        }

    }

}