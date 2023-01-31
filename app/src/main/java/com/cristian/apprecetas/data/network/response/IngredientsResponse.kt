package com.cristian.apprecetas.data.network.response

import com.google.gson.annotations.SerializedName

data class IngredientsResponse(
    @SerializedName("strIngredient")
    val strIngredient: String?,
)
