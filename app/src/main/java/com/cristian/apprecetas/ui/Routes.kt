package com.cristian.apprecetas.ui

sealed class Routes(val route: String) {
    object Screen1 : Routes("recipeView")
}