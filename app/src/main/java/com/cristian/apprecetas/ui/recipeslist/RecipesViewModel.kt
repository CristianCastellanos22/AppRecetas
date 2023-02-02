package com.cristian.apprecetas.ui.recipeslist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristian.apprecetas.data.network.response.ApiResponseStatus
import com.cristian.apprecetas.domain.RecipesUseCase
import com.cristian.apprecetas.domain.model.RecipesUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(private val recipesUseCase: RecipesUseCase) :
    ViewModel() {

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
        if (newValue == SearchWidgetState.CLOSED) {
            recipesList.value = recipesListReserved
        }
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    var recipesList = mutableStateOf<List<RecipesUI>>(listOf())
        private set

    private var recipesListReserved: List<RecipesUI> = listOf()

    var status = mutableStateOf<ApiResponseStatus<Any>?>(null)
        private set

    init {
        getRecipesCollection()
    }

    private fun getRecipesCollection() {
        viewModelScope.launch {
            status.value = ApiResponseStatus.Loading()
            recipesUseCase.invoke().collect {
                handleResponseStatus(it)
            }
        }
    }

    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<List<RecipesUI>>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            recipesList.value = apiResponseStatus.data
        }
        recipesListReserved = recipesList.value
        status.value = apiResponseStatus as ApiResponseStatus<Any>
    }

    fun resetApiResponseStatus() {
        status.value = null
    }

    fun searchRecipesInList(
        searchQuery: String,
    ) {
        recipesList.value = recipesListReserved
        var list: List<RecipesUI>
        list = recipesList.value.filter {
            it.nameMeal.contains(searchQuery, ignoreCase = true)
        }
        if (list.isEmpty()) {
            recipesList.value.forEach { recipe ->
                recipe.data.forEach { ingredient ->
                    ingredient.strIngredient?.let {
                        if (it.contains(searchQuery, ignoreCase = true)) {
                            list = list.plus(recipe)
                        }
                    }
                }
            }
        }
        recipesList.value = list
    }

}