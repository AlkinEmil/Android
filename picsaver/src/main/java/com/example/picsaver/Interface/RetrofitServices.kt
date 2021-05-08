package com.example.picsaver.Interface

import com.example.picsaver.Model.Hit
import com.example.picsaver.Model.Pix
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @GET("?key=21495612-501367ead80ec331b73207556")
    fun getPictureList(@Query("q") q: String,
                       @Query("image_type") image_type: String): Call<Pix>
}