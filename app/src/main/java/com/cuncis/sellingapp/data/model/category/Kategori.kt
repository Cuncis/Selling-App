package com.cuncis.sellingapp.data.model.category


import com.google.gson.annotations.SerializedName


data class Kategori(

	@field:SerializedName("kd_kategori")
	val kdKategori: Long? = null,

	@field:SerializedName("kategori")
	val kategori: String? = null,

	@field:SerializedName("gambar_kategori")
	val gambarKategori: String? = null
)