package com.cristian.apprecetas.ui.recipeslist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristian.apprecetas.data.network.response.ApiResponseStatus
import com.cristian.apprecetas.domain.RecipesUseCase
import com.cristian.apprecetas.domain.model.RecipesUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(private val recipesUseCase: RecipesUseCase): ViewModel() {

    var recipesList = mutableStateOf<List<RecipesUI>>(listOf())
        private set

    var status = mutableStateOf<ApiResponseStatus<Any>?>(null)
        private set

    init {
        getDogCollection()
    }

    private fun getDogCollection() {
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
        status.value = apiResponseStatus as ApiResponseStatus<Any>
    }

    fun resetApiResponseStatus() {
        status.value = null
    }

}