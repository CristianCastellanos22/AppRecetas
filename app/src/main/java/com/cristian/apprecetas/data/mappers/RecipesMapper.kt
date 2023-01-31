package com.cristian.apprecetas.data.mappers

import com.cristian.apprecetas.data.network.response.IngredientsResponse
import com.cristian.apprecetas.data.network.response.RecipesResponse
import com.cristian.apprecetas.domain.model.IngredientsUI
import com.cristian.apprecetas.domain.model.RecipesUI

fun RecipesResponse.toDomain(): RecipesUI {
    return RecipesUI(
        id = id,
        idMeal = idMeal,
        nameMeal = nameMeal,
        nameCategory = nameCategory,
        nameArea = nameArea,
        strInstructions = strInstructions,
        strMealThumb = strMealThumb,
        strTags = strTags,
        data = ArrayList(data.map { it.toDomain() }),
        lat = lat,
        lng = lng
    )
}

fun IngredientsResponse.toDomain(): IngredientsUI {
    return IngredientsUI(
        strIngredient = strIngredient,
    )
}
