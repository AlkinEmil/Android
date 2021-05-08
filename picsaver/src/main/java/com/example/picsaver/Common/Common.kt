package com.example.picsaver.Common

import com.example.picsaver.Interface.RetrofitServices
import com.example.picsaver.Retrofit.RetrofitClient

object Common {
    private const val BASE_URL = "https://pixabay.com/api/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}