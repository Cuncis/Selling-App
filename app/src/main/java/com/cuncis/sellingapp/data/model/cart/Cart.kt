package com.cuncis.sellingapp.data.model.cart


import com.google.gson.annotations.SerializedName


data class Cart(

	@field:SerializedName("harga_rupiah")
	val hargaRupiah: String? = null,

	@field:SerializedName("nama_produk")
	val namaProduk: String? = null,

	@field:SerializedName("jumlah")
	val jumlah: Int? = null,

	@field:SerializedName("harga")
	val harga: Int? = null,

	@field:SerializedName("kd_produk")
	val kdProduk: Int? = null,

	@field:SerializedName("kategori")
	val kategori: String? = null,

	@field:SerializedName("kd_keranjang")
	val kdKeranjang: Int? = null,

	@field:SerializedName("gambar_produk")
	val gambarProduk: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)