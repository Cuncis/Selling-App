package com.cuncis.sellingapp.ui.transaction


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.database.PrefsManager
import com.cuncis.sellingapp.data.model.transaction.Transaksi
import com.cuncis.sellingapp.data.model.transaction.TransaksiResponse
import com.cuncis.sellingapp.util.Constants
import com.cuncis.sellingapp.util.Utils.Companion.showLog
import kotlinx.android.synthetic.main.fragment_transaction.*
import kotlinx.android.synthetic.main.fragment_transaction.view.*

/**
 * A simple [Fragment] subclass.
 */
class TransactionFragment : Fragment(), TransactionContract.View {

    private lateinit var prefsManager: PrefsManager
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var presenter: TransactionPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        prefsManager = PrefsManager(context!!)
        presenter = TransactionPresenter(this)
        initListener(view)
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title = "Transaksi"
        presenter.getTransactionByUsername(prefsManager.prefsUsername)
    }

    override fun initFragment() {
        transactionAdapter = TransactionAdapter(context!!, arrayListOf()) {
            transactionData, position ->
            onClickTransaction(transactionData.noFaktur!!)
        }
    }

    override fun initListener(view: View) {
        val rvTransaction = view.rv_transaction
        val swipe = view.swipe
        val fab = view.fab

        rvTransaction.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionAdapter
        }

        swipe.setOnRefreshListener {
            presenter.getTransactionByUsername(prefsManager.prefsUsername)
        }

        fab.setOnClickListener {

        }
    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResult(transactionResponse: TransaksiResponse) {
        val transactionData: List<Transaksi> = transactionResponse.data!!
        transactionAdapter.setData(transactionData)
    }

    override fun onClickTransaction(invoice: String) {
        Constants.INVOICE = invoice
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.container, TransactionDetailFragment(), "tag_transDetail")
            .commit()
        showLog("Click Detail Transaction")
    }
}
























