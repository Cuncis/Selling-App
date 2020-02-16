package com.cuncis.sellingapp.data.model.cart


import com.google.gson.annotations.SerializedName


data class CartResponse(

	@SerializedName("data")
	val data: List<Cart>? = null
)