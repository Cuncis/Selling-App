package com.cuncis.sellingapp.data.model


import com.google.gson.annotations.SerializedName


data class PegawaiResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("pegawai")
	val pegawai: Pegawai? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)