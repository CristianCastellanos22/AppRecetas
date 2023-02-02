package com.cristian.apprecetas.ui.recipesdetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cristian.apprecetas.domain.model.RecipesUI
import com.cristian.apprecetas.ui.Routes.Screen2
import com.cristian.apprecetas.ui.Routes.Screen3
import com.cristian.apprecetas.ui.theme.AppRecetasTheme

class RecipesDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recipe = intent?.extras?.getParcelable<RecipesUI>(RECIPE_KEY)
        setContent {
            AppRecetasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController,
                        startDestination = Screen2.route) {
                        composable(Screen2.route) {
                            if (recipe != null) {
                                RecipesDetailsScreen(recipesUI = recipe,
                                    navigationController = navigationController)
                            }
                        }
                        composable(Screen3.route,
                            arguments = listOf(navArgument("lat") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val lat = backStackEntry.arguments?.getString("lat")
                            val lng = backStackEntry.arguments?.getString("lng")
                            requireNotNull(lat)
                            requireNotNull(lng)
                            MyMaps(lat = lat.toDouble(), lng = lng.toDouble())
                        }

                    }
                }
            }
        }
    }

    companion object {
        const val RECIPE_KEY = "recipe_key"
    }

}
