package com.cristian.apprecetas.data.mappers

import com.cristian.apprecetas.data.network.response.RecipesResponse
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
        strIngredient1 = strIngredient1,
        strIngredient2 = strIngredient2,
        strIngredient3 = strIngredient3,
        strIngredient4 = strIngredient4,
        strIngredient5 = strIngredient5,
        strIngredient6 = strIngredient6,
        strIngredient7 = strIngredient7,
        strIngredient8 = strIngredient8,
        strIngredient9 = strIngredient9,
        strIngredient10 = strIngredient10,
        strIngredient11 = strIngredient11,
        strIngredient12 = strIngredient12,
        strIngredient13 = strIngredient13,
        strIngredient14 = strIngredient14,
        strIngredient15 = strIngredient15,
        strIngredient16 = strIngredient16,
        strIngredient17 = strIngredient17,
        strIngredient18 = strIngredient18,
        strIngredient19 = strIngredient19,
        strIngredient20 = strIngredient20,
    )
}
