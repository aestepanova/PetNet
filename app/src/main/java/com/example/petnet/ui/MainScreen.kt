package com.example.petnet.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Feed
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.petnet.auth.AuthenticationViewModel
import androidx.compose.ui.graphics.Shape.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.petnet.map.MapViewModel
import com.example.petnet.search.SearchViewModel
import com.example.petnet.ui.theme.Montserrat
import model.Content


@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalAnimationApi
@Composable
fun MainScreen(
    viewModel: MapViewModel,
    searchViewModel: SearchViewModel,
    hideKeyboard: () -> Unit,
    authViewModel: AuthenticationViewModel
) {
    val navImage = listOf(
        Pair(Icons.Outlined.Feed, "Feed"),
        Pair(Icons.Outlined.Bookmark, "Saved"),
        Pair(Icons.Outlined.Person, "Profile")
    )
    val navController = rememberNavController()

    Scaffold(bottomBar = {
        BottomNavigation(
            backgroundColor = Color(0xFF70C1E6),
            contentColor = Color.White,
            elevation = 4.dp,
            modifier = Modifier
                .shadow(6.dp)
                .focusable(true)
        ) {
            Surface(color = Color(0xFF03A9F4)) {
                Row(
                    modifier = Modifier
                        .shadow(4.dp)
                        .padding(bottom = 6.dp)
                ) {}
            }
            navImage.forEach() { screen ->
                BottomNavigationItem(selected = true,
                    onClick = {
                        if (screen.second == "Feed") {
                            navController.navigate("Feed") {
                                launchSingleTop = true
                                restoreState = true
                                if (navController.currentBackStackEntry?.destination?.route == "Saved") {
                                    popUpTo("Saved") {
                                        inclusive = true
                                    }
                                } else {
                                    popUpTo("Profile") {
                                        inclusive = true
                                    }
                                }

                            }
                        } else {
                            navController.navigate(screen.second) {
                                launchSingleTop = true
                                popUpTo("Feed") {
                                    saveState = true
                                }
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = screen.first,
                            contentDescription = null,
                            modifier = Modifier.scale(1.3f)
                        )
                    }
                )
            }
        }
    }) {

        NavHost(navController = navController, startDestination = "Main") {
            composable(route = "Main") { StartScreen(viewModel, navController) }
            composable(route = "Search") {
                SearchScreen(
                    searchViewModel,
                    navController,
                    hideKeyBoard = hideKeyboard
                )
            }
        }

    }
}

@Composable
fun StartScreen(viewModel: MapViewModel, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .clickable { navController.navigate("Feed") }
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)) {
            Text(
                "Pet Net", modifier = Modifier.wrapContentSize(Alignment.Center),
                fontWeight = FontWeight.Bold,
                fontSize = 42.sp,
                color = Color(0xFF03A9F4),
                fontFamily = Montserrat,
            )
        }
        Spacer(Modifier.height(10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)) {
            Text(
                "Just tap to start",
                modifier = Modifier.wrapContentSize(Alignment.Center),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = Color.Black,
                fontFamily = Montserrat,
            )
        }
    }

}






