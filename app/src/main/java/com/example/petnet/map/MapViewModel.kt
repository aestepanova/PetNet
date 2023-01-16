package com.example.petnet.map



import androidx.lifecycle.ViewModel
import com.yandex.mapkit.mapview.MapView
import model.Repository


class MapViewModel(private val repository: Repository) : ViewModel() {
    private val mapView: MapView? = null
    var loading = false


    fun refreshSaved(){

    }
}