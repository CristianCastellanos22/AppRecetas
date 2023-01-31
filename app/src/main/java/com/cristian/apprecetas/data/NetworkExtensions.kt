package com.cristian.apprecetas.data

import retrofit2.Response

fun <T:Any> Response<T>.bodyOrException(): T {
    val body = body()
    if (body != null && isSuccessful) {
        return body
    } else {
        throw Exception("Error: ${errorBody()?.string()}")
    }
}