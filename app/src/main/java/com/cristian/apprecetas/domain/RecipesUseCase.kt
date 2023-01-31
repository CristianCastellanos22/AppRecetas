package com.cristian.apprecetas.domain

import com.cristian.apprecetas.data.network.response.ApiResponseStatus
import com.cristian.apprecetas.domain.model.RecipesUI
import kotlinx.coroutines.flow.Flow

interface RecipesUseCase {
    suspend fun invoke(): Flow<ApiResponseStatus<List<RecipesUI>>>
}