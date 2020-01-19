package com.cuncis.sellingapp.network

import com.cuncis.sellingapp.data.model.PegawaiResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface TheSellingApi {

    @FormUrlEncoded
    @POST("login_pegawai")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<PegawaiResponse>
}