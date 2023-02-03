package com.cristian.apprecetas.data

import com.cristian.apprecetas.data.mappers.toDomain
import com.cristian.apprecetas.data.network.RecipesClient
import com.cristian.apprecetas.data.network.response.ApiResponseStatus
import com.cristian.apprecetas.domain.model.RecipesUI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val recipesServices: RecipesClient,
    private val dispatcher: CoroutineDispatcher,
) : RecipesRepository {
    override suspend fun getRecipes(): Flow<ApiResponseStatus<List<RecipesUI>>> {
        return flow<ApiResponseStatus<List<RecipesUI>>> {
            val response = recipesServices.doGetRecipes().bodyOrException().map { it.toDomain() }
            emit(ApiResponseStatus.Success(response))
        }.onStart {
            emit(ApiResponseStatus.Loading())
        }.catch {
            emit(ApiResponseStatus.Error("Error en consulta \nSugerencias: \n - Verifique su conexión a internet. \n - Intente más tarde."))
        }.flowOn(dispatcher)
    }

}