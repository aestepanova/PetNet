package com.example.petnet.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.petnet.auth.SearchInput
import com.example.petnet.search.SearchEvent
import com.example.petnet.search.SearchViewModel

import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SearchScreen(
    viewmodel: SearchViewModel,
    navController: NavController,
    hideKeyBoard: () -> Unit
) {
    val uiState = viewmodel.uiState.collectAsState().value
    val handleEvent = viewmodel::handleEvent
    Scaffold(modifier = Modifier.fillMaxWidth(), topBar = {
        TopAppBar(
            Modifier.wrapContentSize(Alignment.BottomStart),
            backgroundColor = Color.White,
            elevation = 0.dp
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                ExtendedFloatingActionButton(
                    text = { },
                    onClick = {
                        navController.navigate(route = "Feed") {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo("Search") {
                                inclusive = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    },
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                        .padding(top = 3.dp, end = 3.dp),
                    backgroundColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(0.dp)
                )
                SearchInput(search = uiState.search, onSearchChanged = {
                    handleEvent(SearchEvent.SearchChanged(it))
                }, onNextClicked = {
                    handleEvent(SearchEvent.Search)
                    run(hideKeyBoard)
                }, onClose = { handleEvent(SearchEvent.SearchErased) })
            }
        }
    })
    {
        //val lazyPagingItems = flow.collectAsLazyPagingItems()
        val stateRefresh = rememberSwipeRefreshState(false)
        val loading = viewmodel.uiState.collectAsState()
        SwipeRefresh(
            state = stateRefresh,
            onRefresh = { },
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 60.dp)
            ) {

            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }

}




