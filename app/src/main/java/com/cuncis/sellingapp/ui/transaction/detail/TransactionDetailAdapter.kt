package com.cuncis.sellingapp.ui.transaction.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.model.transaction.detail.DetailTransaksi
import kotlinx.android.synthetic.main.item_transaction_detail.view.*
import java.text.NumberFormat
import java.util.*

import com.cuncis.sellingapp.util.Utils.Companion.setGlideImage

import kotlin.collections.ArrayList

class TransactionDetailAdapter(val context: Context, var details: ArrayList<DetailTransaksi>)
    : RecyclerView.Adapter<TransactionDetailAdapter.TransactionDetailHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionDetailHolder {
        return TransactionDetailHolder(LayoutInflater.from(context).inflate(R.layout.item_transaction_detail, parent, false))
    }

    override fun getItemCount(): Int = details.size

    override fun onBindViewHolder(holder: TransactionDetailHolder, position: Int) {
        holder.bind(details[position])
        context.setGlideImage(holder.view.imvImage, details[position].gambarProduk!!, holder.view.progressBar)
    }

    fun setData(newDetail: List<DetailTransaksi>) {
        details.clear()
        details.addAll(newDetail)
        notifyDataSetChanged()
    }


    inner class TransactionDetailHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(detail: DetailTransaksi) {
            view.txvCategory.text = detail.kategori
            view.txvNameProduct.text = detail.namaProduk
            view.txvPrice.text = "${detail.hargaRupiah} x ${detail.jumlah?.toDouble()}"

            val total: Double = detail.jumlah!!.toDouble() * detail.harga!!.toDouble()
            val totalRupiah: String = NumberFormat.getNumberInstance(Locale.GERMANY).format(total)

            view.txvTotal.text = "Rp ${totalRupiah}"
        }
    }

}