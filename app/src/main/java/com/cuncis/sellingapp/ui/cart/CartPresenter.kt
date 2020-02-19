package com.cuncis.sellingapp.ui.cart

import com.cuncis.sellingapp.data.model.cart.CartResponse
import com.cuncis.sellingapp.data.model.cart.CartUpdateResponse
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
                        showLog("RESPONSE: " + cartResponse?.data)
                        view.onResultCart(cartResponse!!)
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

    override fun deleteItemCart(kdKeranjang: String) {
        view.onLoadingCart(true)
        ApiService.theSellingApi.deleteItemCart(kdKeranjang)
            .enqueue(object : Callback<CartUpdateResponse> {
                override fun onResponse(call: Call<CartUpdateResponse>, response: Response<CartUpdateResponse>) {
                    view.onLoadingCart(false)
                    if (response.isSuccessful) {
                        val cartResponse: CartUpdateResponse? = response.body()
                        showLog("RESPONSE: " + cartResponse?.msg)
                        view.onResultDelete(cartResponse!!)
                    } else {
                        showLog("" + response.message())
                    }
                }
                override fun onFailure(call: Call<CartUpdateResponse>, t: Throwable) {
                    view.onLoadingCart(false)
                    showLog("" + t.message)
                    view.showMessage("" + t.message)
                }
            })
    }

    override fun deleteCart(username: String) {
        view.onLoadingCart(true)
        ApiService.theSellingApi.deleteCart(username)
            .enqueue(object : Callback<CartUpdateResponse> {
                override fun onResponse(call: Call<CartUpdateResponse>, response: Response<CartUpdateResponse>) {
                    view.onLoadingCart(false)
                    if (response.isSuccessful) {
                        val cartResponse: CartUpdateResponse? = response.body()
                        showLog("RESPONSE: " + cartResponse?.msg)
                        view.onResultDelete(cartResponse!!)
                    } else {
                        showLog("" + response.message())
                    }
                }
                override fun onFailure(call: Call<CartUpdateResponse>, t: Throwable) {
                    view.onLoadingCart(false)
                    showLog("" + t.message)
                    view.showMessage("" + t.message)
                }

            })
    }
}





























