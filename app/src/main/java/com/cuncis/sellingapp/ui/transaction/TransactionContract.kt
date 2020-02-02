package com.cuncis.sellingapp.ui.transaction

import com.cuncis.sellingapp.data.model.transaction.TransaksiResponse

interface TransactionContract {

    interface Presenter {
        fun getTransactionByUsername(username: String)
    }

    interface View {
        fun initFragment()
        fun initListener(view: android.view.View)
        fun onLoading(loading: Boolean)
        fun onResult(transactionResponse: TransaksiResponse)
        fun onClickTransaction(invoice: String)
    }

}