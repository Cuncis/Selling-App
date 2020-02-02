package com.cuncis.sellingapp.data.model.transaction


import com.google.gson.annotations.SerializedName


data class Transaksi(

	@field:SerializedName("kd_transaksi")
	val kdTransaksi: Int? = null,

	@field:SerializedName("kd_agen")
	val kdAgen: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("total_rupiah")
	val totalRupiah: String? = null,

	@field:SerializedName("no_faktur")
	val noFaktur: String? = null,

	@field:SerializedName("tgl_penjualan")
	val tglPenjualan: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)