package com.cristian.apprecetas.domain

import com.cristian.apprecetas.data.RecipesRepository
import com.cristian.apprecetas.data.network.response.ApiResponseStatus
import com.cristian.apprecetas.data.network.response.RecipesResponse
import com.cristian.apprecetas.domain.model.RecipesUI
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class RecipesUseCaseImplTest {
    @MockK
    private lateinit var repository: RecipesRepository
    private lateinit var recipesUseCaseImpl: RecipesUseCaseImpl
    private val testDispatcher = TestCoroutineDispatcher()
    private var data = flowOf(ApiResponseStatus.Success(listOf(RecipesUI("1",
        "title",
        "image",
        "description",
        "ingredients",
        "instructions",
        "category",
        "area",
        arrayListOf(),
        0.0,
        0.0))))

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        recipesUseCaseImpl = RecipesUseCaseImpl(repository)
    }

    @Test
    fun `when call getRecipes get successful response`() = runBlocking {
        coEvery {
            repository.getRecipes()
        } returns data

        val response = repository.getRecipes()

        assertEquals(response, data)

    }

    @Test
    fun `when call getRecipes get error response`() = runBlocking {
        coEvery {
            repository.getRecipes()
        } returns flowOf(ApiResponseStatus.Error("Error"))

        val response = repository.getRecipes()
        assertNotEquals(response, data)

    }
}