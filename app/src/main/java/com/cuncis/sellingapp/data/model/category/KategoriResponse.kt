package com.cuncis.sellingapp.data.model.category


import com.google.gson.annotations.SerializedName


data class KategoriResponse(

	@field:SerializedName("data")
	val data: List<Kategori>? = null
)