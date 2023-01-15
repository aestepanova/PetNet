package com.example.petnet.search

sealed class SearchEvent {

    class SearchChanged (val search: String): SearchEvent()

    object Search: SearchEvent()

    object SearchErased: SearchEvent()
}
