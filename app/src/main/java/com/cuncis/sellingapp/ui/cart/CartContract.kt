package com.cuncis.sellingapp.ui.cart

import com.cuncis.sellingapp.data.model.cart.CartResponse

interface CartContract {

    interface Presenter {
        fun getCart(username: String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoadingCart(loading: Boolean)
        fun onResultCart(response: CartResponse)
        fun showMessage(message: String)
    }

}