package com.cristian.apprecetas.data.network

import com.cristian.apprecetas.data.network.response.ApiResponseStatus
import com.cristian.apprecetas.data.network.response.RecipesResponse
import retrofit2.Response
import retrofit2.http.GET

interface RecipesClient {
    @GET("/meals")
    suspend fun doGetRecipes(): Response<List<RecipesResponse>>
}