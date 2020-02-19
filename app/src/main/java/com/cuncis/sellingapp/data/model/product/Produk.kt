package com.cuncis.sellingapp.data.model.product


import com.google.gson.annotations.SerializedName


data class Produk(

	@field:SerializedName("harga_rupiah")
	val hargaRupiah: String? = null,

	@field:SerializedName("nama_produk")
	val namaProduk: String? = null,

	@field:SerializedName("kd_kategori")
	val kdKategori: Int? = null,

	@field:SerializedName("harga")
	val harga: Int? = null,

	@field:SerializedName("kd_produk")
	val kdProduk: Long? = null,

	@field:SerializedName("kategori")
	val kategori: String? = null,

	@field:SerializedName("stok")
	val stok: Int? = null,

	@field:SerializedName("gambar_produk")
	val gambarProduk: String? = null
)