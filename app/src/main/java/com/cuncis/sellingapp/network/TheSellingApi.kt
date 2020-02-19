package com.cuncis.sellingapp.network

import com.cuncis.sellingapp.data.model.agent.AgentDetailResponse
import com.cuncis.sellingapp.data.model.agent.AgentResponse
import com.cuncis.sellingapp.data.model.PegawaiResponse
import com.cuncis.sellingapp.data.model.agent.AgentCreateResponse
import com.cuncis.sellingapp.data.model.agent.AgentUpdateResponse
import com.cuncis.sellingapp.data.model.cart.CartResponse
import com.cuncis.sellingapp.data.model.cart.CartUpdateResponse
import com.cuncis.sellingapp.data.model.category.KategoriResponse
import com.cuncis.sellingapp.data.model.product.ProdukResponse
import com.cuncis.sellingapp.data.model.transaction.TransaksiResponse
import com.cuncis.sellingapp.data.model.transaction.detail.DetailTransaksiResponse
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
    ): Call<AgentCreateResponse>

    @GET("agen/{kd_agen}")
    fun getAgentDetail(
        @Path("kd_agen") kodeAgen: Long
    ): Call<AgentDetailResponse>

    @Multipart
    @POST("agen/{kd_agen}")
    fun updateAgent(
        @Path("kd_agen") kodeAgen: Long,
        @Query("nama_toko") namaToko: String,
        @Query("nama_pemilik") namaPemilik: String,
        @Query("alamat") alamat:String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Part gambarToko: MultipartBody.Part,
        @Query("_method") _method: String
    ): Call<AgentCreateResponse>

    @DELETE("agen/{kd_agen}")
    fun deleteAgent(
        @Path("kd_agen") kodeAgen: Long
    ): Call<AgentUpdateResponse>

    @FormUrlEncoded
    @POST("get_transaksi")
    fun getTransactionByUsername(
        @Field("username") username: String
    ): Call<TransaksiResponse>

    @FormUrlEncoded
    @POST("get_detail_transaksi")
    fun getTransactionByInvoice(
        @Field("no_faktur") noFaktur: String
    ): Call<DetailTransaksiResponse>

    @FormUrlEncoded
    @POST("get_cart")
    fun getCart(
        @Field("username") username: String
    ): Call<CartResponse>

    @FormUrlEncoded
    @POST("add_cart")
    fun addCart(
        @Field("username") username: String,
        @Field("kd_produk") kdProduk: Long,
        @Field("jumlah") jumlah: Long
    ): Call<CartUpdateResponse>

    @GET("get_kategori")
    fun getCategory(): Call<KategoriResponse>

    @FormUrlEncoded
    @POST("get_produk")
    fun getPrductByCategory(
        @Field("kd_kategori") kdKategori: String
    ): Call<ProdukResponse>
}














