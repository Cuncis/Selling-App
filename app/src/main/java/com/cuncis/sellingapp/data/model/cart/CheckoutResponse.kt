package com.cuncis.sellingapp.data.model.cart


import com.google.gson.annotations.SerializedName


data class CheckoutResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)