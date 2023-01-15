package com.example.petnet.map



import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import model.Content
import model.ContentResponseDto

import model.Repository
import java.util.*


class MapViewModel(private val repository: Repository) : ViewModel() {
    var prPage = mutableStateOf(0)
    var loading = false


    fun refreshSaved(){

    }
}