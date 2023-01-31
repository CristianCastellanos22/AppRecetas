package com.cristian.apprecetas.ui.recipesdetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker

@Composable
fun MyMaps(
    lat: Double,
    lng: Double
) {
    //val marker = LatLng(4.659075190927651, -74.09372545494584)
    val marker = LatLng(lat, lng)
    println("lat: $lat, lng: $lng")
    //val marker = LatLng(lat, 0.0)
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        uiSettings = MapUiSettings(zoomControlsEnabled = true)
    ) {
        Marker(position = marker)
    }
}