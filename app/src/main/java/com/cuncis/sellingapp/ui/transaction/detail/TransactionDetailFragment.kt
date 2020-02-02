package com.cuncis.sellingapp.ui.transaction.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.model.transaction.detail.DetailTransaksiResponse
import com.cuncis.sellingapp.util.Constants
import kotlinx.android.synthetic.main.fragment_transaction_detail.*
import kotlinx.android.synthetic.main.fragment_transaction_detail.view.*

/**
 * A simple [Fragment] subclass.
 */
class TransactionDetailFragment : Fragment(), TransactionDetailContract.View {

    private lateinit var detailAdapter: TransactionDetailAdapter
    private lateinit var presenter: TransactionDetailPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = TransactionDetailPresenter(this)
        initListener(view)
    }

    override fun initFragment() {
        detailAdapter = TransactionDetailAdapter(context!!, arrayListOf())
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title = "Detail Transaksi"
        presenter.getTransactionByInvoice(Constants.INVOICE)
    }

    override fun initListener(view: View) {
        val tvInvoice = view.txvInvoice
        val rvDetail = view.rcvDetail
        val swipe = view.swipe

        tvInvoice.text = Constants.INVOICE
        rvDetail.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = detailAdapter
        }

        swipe.setOnRefreshListener {
            presenter.getTransactionByInvoice(Constants.INVOICE)
        }
    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResult(transactionDetailResponse: DetailTransaksiResponse) {
        val detailList = transactionDetailResponse.data
        detailAdapter.setData(detailList!!)
    }


}
