package com.cuncis.sellingapp.ui.cart

import com.cuncis.sellingapp.data.model.cart.CartResponse
import com.cuncis.sellingapp.data.model.cart.CartUpdateResponse
import com.cuncis.sellingapp.data.model.cart.CheckoutResponse

interface CartContract {

    interface Presenter {
        fun getCart(username: String)

        fun deleteItemCart(kdKeranjang: String)
        fun deleteCart(username: String)

        fun checkout(username: String, kdAgent: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoadingCart(loading: Boolean)
        fun onResultCart(response: CartResponse)
        fun showMessage(message: String)

        fun onResultDelete(response: CartUpdateResponse)
        fun showDialog()

        fun onLoadingCheckout(loading: Boolean)
        fun onResultCheckout(response: CheckoutResponse)
    }

}