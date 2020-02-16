package com.cuncis.sellingapp.ui.cart.add

import com.cuncis.sellingapp.data.model.cart.CartUpdateResponse


interface CartAddContract {

    interface Presenter {
        fun addCart(username: String, kdProduk: Long, jumlah: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(response: CartUpdateResponse)
        fun showMessage(message: String)
    }

}