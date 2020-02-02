package com.cuncis.sellingapp.ui.transaction.detail

import com.cuncis.sellingapp.data.model.transaction.TransaksiResponse
import com.cuncis.sellingapp.data.model.transaction.detail.DetailTransaksiResponse

interface TransactionDetailContract {

    interface Presenter {
        fun getTransactionByInvoice(invoice: String)
    }

    interface View {
        fun initFragment()
        fun initListener(view: android.view.View)
        fun onLoading(loading: Boolean)
        fun onResult(transactionDetailResponse: DetailTransaksiResponse)
    }

}