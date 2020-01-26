package com.cuncis.sellingapp.data.model


import com.google.gson.annotations.SerializedName


data class AgentUpdateResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("msg")
	val msgSukses: Sukses? = null,

	@field:SerializedName("msg")
	val msgNamaToko: NamaTokoMsg? = null,

	@field:SerializedName("msg")
	val msgNamaPemilik: NamaPemilikMsg? = null,

	@field:SerializedName("msg")
	val msgAlamat: AlamatMsg? = null,

	@field:SerializedName("msg")
	val msgLatitude: LatitudeMsg? = null,

	@field:SerializedName("msg")
	val msgLongitude: LongitudeMsg? = null,

	@field:SerializedName("msg")
	val msgGambarToko: GambarTokoMsg? = null,

	@field:SerializedName("status")
	val status: Boolean? = null

)

data class Sukses(@SerializedName("sukses") val list: List<String>)

data class NamaTokoMsg(@SerializedName("nama_toko") val list: List<String>)

data class NamaPemilikMsg(@SerializedName("nama_pemilik") val list: List<String>)

data class AlamatMsg(@SerializedName("alamat") val list: List<String>)

data class LatitudeMsg(@SerializedName("latitude") val list: List<String>)

data class LongitudeMsg(@SerializedName("longitude") val list: List<String>)

data class GambarTokoMsg(@SerializedName("gambar_toko") val list: List<String>)