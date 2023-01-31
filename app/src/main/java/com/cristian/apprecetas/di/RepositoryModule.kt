package com.cristian.apprecetas.di

import com.cristian.apprecetas.data.RecipesRepositoryImpl
import com.cristian.apprecetas.data.RecipesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(
        recipesRepositoryImpl: RecipesRepositoryImpl
    ): RecipesRepository
}