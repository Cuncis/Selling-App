package com.cuncis.sellingapp.ui.cart.add

import com.cuncis.sellingapp.data.model.cart.CartUpdateResponse
import com.cuncis.sellingapp.network.ApiService
import com.cuncis.sellingapp.util.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartAddPresenter(val view: CartAddContract.View): CartAddContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun addCart(username: String, kdProduk: Long, jumlah: Long) {
        view.onLoading(true)
        ApiService.theSellingApi.addCart(username, kdProduk, jumlah)
            .enqueue(object : Callback<CartUpdateResponse> {
                override fun onResponse(call: Call<CartUpdateResponse>, response: Response<CartUpdateResponse>) {
                    view.onLoading(false)
                    if (response.isSuccessful) {
                        val cartAddResponse = response.body()
                        view.showMessage("" + response.body()?.msg)
                        view.onResult(cartAddResponse!!)
                    } else {
                        Utils.showLog("Error1: " + response.message())
                    }
                }
                override fun onFailure(call: Call<CartUpdateResponse>, t: Throwable) {
                    view.onLoading(false)
                    Utils.showLog("" + t.message)
                }
            })
    }

}