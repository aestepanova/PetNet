package com.example.petnet.search

import androidx.annotation.StringRes
import com.example.petnet.R


data class SearchState (

    val search: String = "",
    val error: String? = null,
    val isLoading: Boolean = false
){
    fun isFormValid(): Boolean {
            return search.isNotEmpty()
        }
    }

