package com.cristian.apprecetas.data.network

import com.cristian.apprecetas.data.network.response.ApiResponseStatus
import com.cristian.apprecetas.data.network.response.RecipesResponse
import com.cristian.apprecetas.domain.model.RecipesUI
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    suspend fun getRecipes(): Flow<ApiResponseStatus<List<RecipesUI>>>
}