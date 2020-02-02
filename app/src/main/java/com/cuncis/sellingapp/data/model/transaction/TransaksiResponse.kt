package com.cuncis.sellingapp.data.model.transaction


import com.google.gson.annotations.SerializedName


data class TransaksiResponse(

	@field:SerializedName("data")
	val data: List<Transaksi>? = null
)