package com.cuncis.sellingapp.x


import com.google.gson.annotations.SerializedName


data class Msg(

	@field:SerializedName("sukses")
	val sukses: List<String?>? = null,

	@field:SerializedName("nama_toko")
	val namaToko: List<String?>? = null,

	@field:SerializedName("nama_pemilik")
	val namaPemilik: List<String?>? = null,

	@field:SerializedName("alamat")
	val alamat: List<String?>? = null,

	@field:SerializedName("latitude")
	val latitude: List<String?>? = null,

	@field:SerializedName("longitude")
	val longitude: List<String?>? = null,

	@field:SerializedName("gambar_toko")
	val gambarToko: List<String?>? = null
)