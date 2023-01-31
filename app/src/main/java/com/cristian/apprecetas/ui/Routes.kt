package com.cristian.apprecetas.ui

sealed class Routes(val route: String) {
    object Screen1 : Routes("recipeView")
    object Screen2 : Routes("recipeDetailsView")
    object Screen3 : Routes("recipeMapView/{lat}/{lng}") {
        fun createRoute(lat: Double, lng: Double) = "recipeMapView/$lat/$lng"
    }
}