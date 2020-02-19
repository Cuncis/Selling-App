package com.cuncis.sellingapp.data.model.product


import com.google.gson.annotations.SerializedName


data class ProdukResponse(

	@field:SerializedName("data")
	val data: List<Produk>? = null
)