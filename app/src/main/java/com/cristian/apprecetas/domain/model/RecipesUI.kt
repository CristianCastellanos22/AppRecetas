package com.cristian.apprecetas.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class RecipesUI(
    val id: String,
    val idMeal: String,
    val nameMeal: String,
    val nameCategory: String,
    val nameArea: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strTags: String,
    val data: @RawValue ArrayList<IngredientsUI>,
    val lat: Double,
    val lng: Double
) : Parcelable