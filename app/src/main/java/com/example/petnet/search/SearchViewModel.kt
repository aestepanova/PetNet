package com.example.petnet.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import model.Repository

class SearchViewModel(private val repository: Repository
) : ViewModel() {
    val uiState = MutableStateFlow(SearchState())

    fun handleEvent(searchEvent: SearchEvent) {
        when (searchEvent) {
            is SearchEvent.SearchChanged -> {
                updateSearch(search = searchEvent.search)
            }
            is SearchEvent.Search -> {
                searchPlaces(tagsList = uiState.value.search)
            }
            is SearchEvent.SearchErased ->{
                eraseSearch()
            }
        }
    }
    private fun updateSearch(search: String) {
        uiState.value = uiState.value.copy(
            search = search
        )
    }
    private fun eraseSearch() {
        uiState.value = uiState.value.copy(
            search = ""
        )
    }
    private fun searchPlaces(tagsList: String) {
        uiState.value = uiState.value.copy(
            isLoading = true
        )
    }

}