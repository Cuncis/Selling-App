package com.cuncis.sellingapp.ui.cart

import com.cuncis.sellingapp.data.model.cart.CartResponse
import com.cuncis.sellingapp.network.ApiService
import com.cuncis.sellingapp.util.Utils.Companion.showLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartPresenter(val view: CartContract.View): CartContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getCart(username: String) {
        view.onLoadingCart(true)
        ApiService.theSellingApi.getCart(username)
            .enqueue(object : Callback<CartResponse> {
                override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                    view.onLoadingCart(false)
                    if (response.isSuccessful) {
                        val cartResponse: CartResponse? = response.body()
                        showLog("" + cartResponse?.data)
//                        view.onResultCart(cartResponse!!)
                    } else {
                        showLog("" + response.message())
                    }
                }
                override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                    view.onLoadingCart(false)
                    showLog("" + t.message)
                    view.showMessage("" + t.message)
                }
            })
    }
}