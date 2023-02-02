package com.cristian.apprecetas.ui.recipesdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.cristian.apprecetas.R
import com.cristian.apprecetas.domain.model.RecipesUI
import com.cristian.apprecetas.ui.Routes.Screen3

@Composable
fun RecipesDetailsScreen(recipesUI: RecipesUI, navigationController: NavHostController) {
    Scaffold(
        topBar = { RecipesDetailsScreenTopBar(recipesUI) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            MyDivider()
            AsyncImage(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
                model = recipesUI.strMealThumb,
                contentDescription = "", placeholder = painterResource(id = R.drawable.loading_img),
                error = painterResource(id = R.drawable.ic_broken_image)
            )
            MyDivider()
            Text(text = "País de origen:",
                modifier = Modifier.padding(start = 8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(R.drawable.baseline_circle_24),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(8.dp)
                        .width(8.dp))
                Text(text = recipesUI.nameArea,
                    modifier = Modifier
                        .padding(8.dp),
                    color = Color.Gray)
                Button(
                    onClick = {
                        navigationController.navigate(Screen3.createRoute(recipesUI.lat,
                            recipesUI.lng))
                    },
                    shape = RoundedCornerShape(20.dp),
                ) {
                    Icon(Icons.Sharp.Place, contentDescription = null)
                    Text(text = "Ver en mapa")
                }
            }
            MyDivider()
            Text(text = stringResource(id = R.string.ingredient),
                modifier = Modifier.padding(start = 8.dp))
            MyDivider()
            recipesUI.data.forEach {
                it.strIngredient?.let { it1 -> TextIngredients(text = it1) }
            }
            MyDivider()
            Text(text = stringResource(id = R.string.instructions),
                modifier = Modifier.padding(start = 8.dp))
            MyDivider()
            Text(text = recipesUI.strInstructions,
                modifier = Modifier.padding(start = 8.dp),
                color = Color.Gray)
        }
    }
}

@Composable
fun TextIngredients(text: String) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(painter = painterResource(R.drawable.baseline_circle_24),
            contentDescription = "",
            modifier = Modifier
                .padding(start = 8.dp)
                .height(8.dp)
                .width(8.dp))
        Text(text = text, modifier = Modifier.padding(8.dp), color = Color.Gray)
    }
}

@Composable
fun RecipesDetailsScreenTopBar(recipesUI: RecipesUI) {
    TopAppBar(
        title = { Text(text = recipesUI.nameMeal) },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
    )
}

@Composable
fun MyDivider() {
    Divider(
        modifier = Modifier.padding(
            top = 8.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 16.dp
        ),
        color = Color.Gray,
        thickness = 1.dp
    )
}
