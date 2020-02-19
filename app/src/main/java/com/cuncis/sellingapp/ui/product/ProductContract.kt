package com.cuncis.sellingapp.ui.product

import com.cuncis.sellingapp.data.model.category.KategoriResponse
import com.cuncis.sellingapp.data.model.product.ProdukResponse

interface ProductContract {

    interface Presenter {
        fun getCategory()
        fun getProduct(kdKategori: String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResultCategory(response: KategoriResponse)
        fun onResultProduct(response: ProdukResponse)
    }

}