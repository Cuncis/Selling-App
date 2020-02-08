package com.cuncis.sellingapp.data.model.cart


import com.google.gson.annotations.SerializedName


data class CartResponse(

	@field:SerializedName("data")
	val data: List<Cart>? = null
)