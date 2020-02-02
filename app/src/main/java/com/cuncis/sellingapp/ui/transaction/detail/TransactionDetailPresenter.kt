package com.cuncis.sellingapp.ui.transaction.detail

import com.cuncis.sellingapp.data.model.transaction.detail.DetailTransaksiResponse
import com.cuncis.sellingapp.network.ApiService
import com.cuncis.sellingapp.util.Utils.Companion.showLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionDetailPresenter(val view: TransactionDetailContract.View): TransactionDetailContract.Presenter {

    init {
        view.initFragment()
    }

    override fun getTransactionByInvoice(invoice: String) {
        view.onLoading(true)
        ApiService.theSellingApi.getTransactionByInvoice(invoice)
            .enqueue(object : Callback<DetailTransaksiResponse> {
                override fun onResponse(call: Call<DetailTransaksiResponse>, response: Response<DetailTransaksiResponse>) {
                    view.onLoading(false)
                    if (response.isSuccessful) {
                        val transactionDetailResponse = response.body()
                        view.onResult(transactionDetailResponse!!)
                    } else {
                        showLog("Error1: " + response.message())
                    }
                }
                override fun onFailure(call: Call<DetailTransaksiResponse>, t: Throwable) {
                    view.onLoading(false)
                    showLog("Error2: " + t.message)
                }
            })
    }

}