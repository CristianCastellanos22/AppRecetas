package com.cristian.apprecetas.domain

import com.cristian.apprecetas.data.network.RecipesRepository
import javax.inject.Inject

class RecipesUseCaseImpl @Inject constructor(private val repository: RecipesRepository) :
    RecipesUseCase {
    override suspend fun invoke() = repository.getRecipes()
}
