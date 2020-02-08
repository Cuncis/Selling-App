package com.cuncis.sellingapp.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.model.cart.Cart
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

import com.cuncis.sellingapp.util.Utils.Companion.setGlideImage
import kotlinx.android.synthetic.main.item_cart.view.*
import kotlinx.android.synthetic.main.item_transaction_detail.view.imvImage
import kotlinx.android.synthetic.main.item_transaction_detail.view.progressBar
import kotlinx.android.synthetic.main.item_transaction_detail.view.txvCategory
import kotlinx.android.synthetic.main.item_transaction_detail.view.txvNameProduct
import kotlinx.android.synthetic.main.item_transaction_detail.view.txvPrice
import kotlinx.android.synthetic.main.item_transaction_detail.view.txvTotal

class CartAdapter(val context: Context, var cartList: ArrayList<Cart>,
                  val clickListener: (Cart, Int) -> Unit): RecyclerView.Adapter<CartAdapter.CartHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder =
        CartHolder(LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false))

    override fun getItemCount(): Int = cartList.size

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        holder.bind(cartList[position])
        context.setGlideImage(holder.view.imvImage, cartList[position].gambarProduk!!, holder.view.progressBar)
        holder.view.imvDelete.setOnClickListener {
            clickListener(cartList[position], position)
            removeCart(position)
        }
    }

    fun setCartList(newCart: List<Cart>) {
        cartList.clear()
        cartList.addAll(newCart)
        notifyDataSetChanged()
    }

    fun removeCart(position: Int) {
        cartList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, cartList.size)
    }

    fun removeAll() {
        cartList.clear()
        notifyDataSetChanged()
    }

    inner class CartHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(cart: Cart) {
            view.txvCategory.text = cart.kategori
            view.txvNameProduct.text = cart.namaProduk
            view.txvPrice.text = "${cart.hargaRupiah} x${cart.jumlah}"

            val total: Double = cart.jumlah!!.toDouble() * cart.harga!!.toDouble()
            val totalRupiah: String = NumberFormat.getNumberInstance(Locale.GERMANY).format(total)
            view.txvTotal.text = "Rp $totalRupiah"
        }
    }
}