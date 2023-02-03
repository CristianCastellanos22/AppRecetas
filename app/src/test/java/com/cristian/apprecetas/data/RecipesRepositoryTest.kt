package com.cristian.apprecetas.data

import com.cristian.apprecetas.data.network.RecipesClient
import com.cristian.apprecetas.data.network.response.ApiResponseStatus
import com.cristian.apprecetas.data.network.response.RecipesResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class RecipesRepositoryImplTest {

    @MockK
    private lateinit var recipesServices: RecipesClient
    private lateinit var recipesRepositoryImpl: RecipesRepositoryImpl
    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        recipesRepositoryImpl = RecipesRepositoryImpl(recipesServices, dispatcher)
    }

    @Test
    fun `getRecipes should return success`() = runBlocking {
        // Given
        val recipesResponseList = listOf(RecipesResponse("1",
            "name",
            "image",
            "description",
            "ingredients",
            "instructions",
            "category",
            "area",
            arrayListOf(),
            0.0,
            0.0))

        coEvery { recipesServices.doGetRecipes() } returns Response.success(recipesResponseList)

        // When
        val result = runBlocking { recipesRepositoryImpl.getRecipes().toList() }

        // Then
        assertEquals(2, result.size)
        assertEquals((result[0] is ApiResponseStatus.Loading), true)
    }

    @Test
    fun `getRecipes should return error`() = runBlocking {
        // Given
        coEvery { recipesServices.doGetRecipes() } throws Exception("Error en consulta")

        // When
        val result = runBlocking { recipesRepositoryImpl.getRecipes().toList() }

        // Then
        assert(result[1] is ApiResponseStatus.Error)
        assertEquals(true, (result[1] is ApiResponseStatus.Error))
        assertEquals(true, (result[0] is ApiResponseStatus.Loading))
    }
}
