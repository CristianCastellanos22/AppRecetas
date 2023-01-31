package com.cristian.apprecetas.utils

import retrofit2.Response

fun <T:Any> Response<T>.bodyOrException(): T {
    val body = body()
    if (body != null && isSuccessful) {
        return body
    } else {
        throw Exception("Error: ${errorBody()?.string()}")
    }
}