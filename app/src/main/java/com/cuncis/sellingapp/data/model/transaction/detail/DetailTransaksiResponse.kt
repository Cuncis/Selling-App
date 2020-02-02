package com.cuncis.sellingapp.data.model.transaction.detail


import com.google.gson.annotations.SerializedName


data class DetailTransaksiResponse(

	@field:SerializedName("data")
	val data: List<DetailTransaksi>? = null
)