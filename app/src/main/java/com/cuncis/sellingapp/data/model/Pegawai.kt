package com.cuncis.sellingapp.data.model


import com.google.gson.annotations.SerializedName


data class Pegawai(

	@field:SerializedName("jk")
	val jk: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("nama_pegawai")
	val namaPegawai: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("is_aktif")
	val isAktif: Int? = null
)