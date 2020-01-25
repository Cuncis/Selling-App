package com.cuncis.sellingapp.network

import com.cuncis.sellingapp.data.model.AgentResponse
import com.cuncis.sellingapp.data.model.AgentUpdateResponse
import com.cuncis.sellingapp.data.model.PegawaiResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface TheSellingApi {

    @FormUrlEncoded
    @POST("login_pegawai")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<PegawaiResponse>

    @GET("agen")
    fun getAgent(): Call<AgentResponse>

    @Multipart
    @POST("agen")
    fun insertAgent(
        @Query("nama_toko") namaToko: String,
        @Query("nama_pemilik") namaPemilik: String,
        @Query("alamat") alamat: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Part gambarToko: MultipartBody.Part
    ): Call<AgentUpdateResponse>
}