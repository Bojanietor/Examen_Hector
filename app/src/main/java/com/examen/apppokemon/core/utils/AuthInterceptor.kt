package com.examen.apppokemon.core.utils

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer TU_TOKEN_AQUI") // Agregar encabezado de autenticación
            .build()
       Log.d("LA URL:",request.url.toString())
        return chain.proceed(request)
    }



}