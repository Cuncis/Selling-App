package com.cuncis.sellingapp.ui.product

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.model.product.Produk
import com.cuncis.sellingapp.util.Constants
import com.cuncis.sellingapp.util.Utils.Companion.setGlideImage
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(val context: Context, var productList: ArrayList<Produk>)
    : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(LayoutInflater.from(context).inflate(R.layout.item_product, parent, false))
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(productList[position])
        context.setGlideImage(holder.view.imvImage, productList[position].gambarProduk!!, holder.view.progressBar)
        holder.view.linProduct.setOnClickListener {
            Constants.PRODUCT_ID = productList[position].kdProduk!!
            Constants.PRODUCT_NAME = productList[position].namaProduk!!
            Constants.IS_CHANGED = true
            (context as Activity).finish()
        }
    }

    fun setData(newProduct: List<Produk>) {
        productList.clear()
        productList.addAll(newProduct)
        notifyDataSetChanged()
    }

    inner class ProductHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(produk: Produk) {
            view.txvName.text = produk.namaProduk
            view.txvPrice.text = produk.hargaRupiah
            view.txvStock.text = "Stok Tersedia (${produk.stok})"
        }
    }
}