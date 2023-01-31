package com.cristian.apprecetas.ui.recipeslist

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cristian.apprecetas.data.network.response.ApiResponseStatus
import com.cristian.apprecetas.domain.model.RecipesUI
import com.cristian.apprecetas.ui.composables.ErrorDialog
import com.cristian.apprecetas.ui.composables.LoadingWheel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipesListScreen(recipesViewModel: RecipesViewModel) {

    val status = recipesViewModel.status.value
    val recipes = recipesViewModel.recipesList.value.groupBy { it.strTags }
    val context = LocalContext.current

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        recipes.forEach { (strTags, recipesUI) ->
            stickyHeader {
                Text(
                    text = strTags,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray),
                    fontSize = 16.sp
                )
            }
            items(recipesUI) {
                RecipeItems(recipesUI = it) {
                    Toast.makeText(
                        context,
                        it.nameMeal,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


        }
    }

    if (status is ApiResponseStatus.Loading) {
        LoadingWheel()
    } else if (status is ApiResponseStatus.Error) {
        ErrorDialog(status.messageId) { recipesViewModel.resetApiResponseStatus() }
    }
}

@Composable
fun RecipeItems(recipesUI: RecipesUI, onRecipeClicked: (RecipesUI) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRecipeClicked(recipesUI) },
        elevation = 15.dp,
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(2.dp, Color.White, RoundedCornerShape(8.dp)),
                model = recipesUI.strMealThumb,
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = recipesUI.nameCategory, color = Color.Gray)
                Text(text = recipesUI.nameMeal, fontSize = 20.sp)
                Text(text = recipesUI.nameArea, color = Color.Gray)
            }
        }
    }
}