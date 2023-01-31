package com.cristian.apprecetas.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientsUI(
    val strIngredient: String?
) : Parcelable
