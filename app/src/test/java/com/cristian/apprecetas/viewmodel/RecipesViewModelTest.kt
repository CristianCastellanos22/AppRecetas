package com.cristian.apprecetas.viewmodel

import com.cristian.apprecetas.data.network.response.ApiResponseStatus
import com.cristian.apprecetas.domain.RecipesUseCase
import com.cristian.apprecetas.domain.model.RecipesUI
import com.cristian.apprecetas.ui.recipeslist.RecipesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class RecipesViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = RecipeCoroutineRule()

    @Test
    fun testGetRecipesCollectionOK(): Unit = runBlocking {
        class FakeRecipesUseCase : RecipesUseCase {
            override suspend fun invoke(): Flow<ApiResponseStatus<List<RecipesUI>>> {
                return flowOf(ApiResponseStatus.Success(listOf(
                    RecipesUI(
                        id = "1",
                        idMeal = "title",
                        nameMeal = "image",
                        nameCategory = "imageType",
                        nameArea = "1",
                        strInstructions = "",
                        strMealThumb = "",
                        strTags = "",
                        data = arrayListOf(),
                        lat = 0.0,
                        lng = 0.0
                    )
                )))
            }
        }

        val viewModel = RecipesViewModel(
            recipesUseCase = FakeRecipesUseCase()
        )

        assertEquals(1, viewModel.recipesList.value.size)
        assert(viewModel.status.value is ApiResponseStatus.Success)
    }

    @Test
    fun testGetRecipesCollection(): Unit = runBlocking {
        class FakeRecipesUseCase : RecipesUseCase {
            override suspend fun invoke(): Flow<ApiResponseStatus<List<RecipesUI>>> {
                return flowOf(ApiResponseStatus.Success(listOf()))
            }
        }

        val viewModel = RecipesViewModel(
            recipesUseCase = FakeRecipesUseCase()
        )

        assertEquals(0, viewModel.recipesList.value.size)
        assert(viewModel.recipesList.value.isEmpty())
    }

    @Test
    fun testGetRecipesCollectionError(): Unit = runBlocking {
        class FakeRecipesUseCase : RecipesUseCase {
            override suspend fun invoke(): Flow<ApiResponseStatus<List<RecipesUI>>> {
                return flowOf(ApiResponseStatus.Error("Error"))
            }
        }

        val viewModel = RecipesViewModel(
            recipesUseCase = FakeRecipesUseCase()
        )

        assertEquals(0, viewModel.recipesList.value.size)
        assert(viewModel.status.value is ApiResponseStatus.Error)
    }
}