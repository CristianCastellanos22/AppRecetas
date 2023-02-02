package com.cristian.apprecetas.ui.recipeslist

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cristian.apprecetas.R
import com.cristian.apprecetas.data.network.response.ApiResponseStatus
import com.cristian.apprecetas.domain.model.RecipesUI
import com.cristian.apprecetas.ui.Routes.*
import com.cristian.apprecetas.ui.composables.ErrorDialog
import com.cristian.apprecetas.ui.composables.LoadingWheel
import com.cristian.apprecetas.ui.theme.Purple500

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RecipesListScreen(
    recipesViewModel: RecipesViewModel,
    onRecipeClicked: (RecipesUI) -> Unit,
) {

    val searchWidgetState by recipesViewModel.searchWidgetState
    val searchTextState by recipesViewModel.searchTextState

    val status = recipesViewModel.status.value
    val recipes = recipesViewModel.recipesList.value.groupBy { it.strTags }
    val context = LocalContext.current

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    recipesViewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    recipesViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    recipesViewModel.searchRecipesInList(it)
                }, onSearchTriggered = {
                    recipesViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        }
    ) {
        ShowRecipes(
            recipes = recipes,
            onRecipeClicked = onRecipeClicked,
            recipesViewModel = recipesViewModel,
        )
    }

    if (status is ApiResponseStatus.Loading) {
        LoadingWheel()
    } else if (status is ApiResponseStatus.Error) {
        ErrorDialog(status.messageId) { recipesViewModel.resetApiResponseStatus() }
    } else if (recipes.isEmpty()) {
        Toast.makeText(context, "No se encontraron resultados", Toast.LENGTH_SHORT).show()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowRecipes(
    recipes: Map<String, List<RecipesUI>>,
    onRecipeClicked: (RecipesUI) -> Unit,
    recipesViewModel: RecipesViewModel,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        recipes.forEach { (strTags, recipesUI) ->
            stickyHeader {
                Text(
                    text = strTags,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    fontSize = 16.sp
                )
            }
            items(recipesUI) {
                RecipeItems(
                    recipesUI = it,
                    onRecipeClicked = onRecipeClicked,
                    recipesViewModel = recipesViewModel,
                )
            }

        }
    }

}

@Composable
fun RecipeItems(
    recipesUI: RecipesUI,
    onRecipeClicked: (RecipesUI) -> Unit,
    recipesViewModel: RecipesViewModel,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                recipesViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                onRecipeClicked(recipesUI)
            },
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
                placeholder = painterResource(id = R.drawable.loading_img),
                error = painterResource(id = R.drawable.ic_broken_image)
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

@Composable
fun MainAppBar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            RecipesListScreenTopBar(
                onSearchIconClicked = onSearchTriggered,
            )
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}

@Composable
fun RecipesListScreenTopBar(
    onSearchIconClicked: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = "Recetas") },
        backgroundColor = Purple500,
        contentColor = Color.White,
        actions = {
            IconButton(onClick = { onSearchIconClicked() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_search_24),
                    contentDescription = "Search"
                )
            }
        }
    )
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary
    ) {
        TextField(modifier = Modifier
            .fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    text = "Search here...",
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            ))
    }
}
