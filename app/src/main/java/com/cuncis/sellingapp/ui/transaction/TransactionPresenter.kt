package com.cuncis.sellingapp.ui.transaction

import com.cuncis.sellingapp.data.model.transaction.TransaksiResponse
import com.cuncis.sellingapp.network.ApiService
import com.cuncis.sellingapp.util.Utils.Companion.showLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionPresenter(val view: TransactionContract.View): TransactionContract.Presenter {

    init {
        view.initFragment()
    }

    override fun getTransactionByUsername(username: String) {
        view.onLoading(true)
        ApiService.theSellingApi.getTransactionByUsername(username)
            .enqueue(object : Callback<TransaksiResponse> {
                override fun onResponse(call: Call<TransaksiResponse>, response: Response<TransaksiResponse>) {
                    view.onLoading(false)
                    if (response.isSuccessful) {
                        val transactionResponse = response.body()
                        view.onResult(transactionResponse!!)
                    } else {
                        showLog("Error1: " + response.message())
                    }
                }

                override fun onFailure(call: Call<TransaksiResponse>, t: Throwable) {
                    view.onLoading(false)
                    showLog("Error2: " + t.message)
                }
            })
    }
}