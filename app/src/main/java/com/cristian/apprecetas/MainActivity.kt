package com.cristian.apprecetas

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cristian.apprecetas.domain.model.RecipesUI
import com.cristian.apprecetas.ui.Routes.Screen1
import com.cristian.apprecetas.ui.recipesdetails.RecipesDetailsActivity
import com.cristian.apprecetas.ui.recipeslist.RecipesListScreen
import com.cristian.apprecetas.ui.recipeslist.RecipesViewModel
import com.cristian.apprecetas.ui.theme.AppRecetasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val recipesViewModel: RecipesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppRecetasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Screen1.route
                    ) {
                        composable(Screen1.route) {
                            RecipesListScreen(
                                recipesViewModel = recipesViewModel,
                                onRecipeClicked = ::openRecipesDetailActivity,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun openRecipesDetailActivity(recipe: RecipesUI) {
        val intent = Intent(this, RecipesDetailsActivity::class.java)
        intent.putExtra("recipe_key", recipe)
        startActivity(intent)
    }

    private fun onNavigationIconClick() {
        finish()
    }
}

