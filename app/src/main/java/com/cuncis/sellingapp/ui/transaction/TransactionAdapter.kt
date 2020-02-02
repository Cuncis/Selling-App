package com.cuncis.sellingapp.ui.transaction

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.model.transaction.Transaksi
import kotlinx.android.synthetic.main.item_transaction.view.*

class TransactionAdapter(val context: Context, var transactions: ArrayList<Transaksi>,
                         val clicklistener: (Transaksi, Int) -> Unit)
    : RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        return TransactionHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false))
    }

    override fun getItemCount(): Int = transactions.size

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        holder.bind(transactions[position])
        holder.view.txvSee.setOnClickListener {
            clicklistener(transactions[position], position)
        }
    }

    fun setData(newTransaction: List<Transaksi>) {
        transactions.clear()
        transactions.addAll(newTransaction)
        notifyDataSetChanged()
    }

    inner class TransactionHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(transaction: Transaksi) {
            view.txvInvoice.text = transaction.noFaktur
            view.txvDate.text = transaction.tglPenjualan
            view.txvTotal.text = transaction.totalRupiah
        }
    }
}