package com.cuncis.sellingapp.ui.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.database.PrefsManager
import com.cuncis.sellingapp.data.model.cart.Cart
import com.cuncis.sellingapp.data.model.cart.CartResponse
import com.cuncis.sellingapp.data.model.cart.CartUpdateResponse
import com.cuncis.sellingapp.data.model.cart.CheckoutResponse
import com.cuncis.sellingapp.ui.agent.search.AgentSearchActivity
import com.cuncis.sellingapp.ui.cart.add.CartAddActivity
import com.cuncis.sellingapp.util.Constants
import com.cuncis.sellingapp.util.Utils.Companion.showLog
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity(), CartContract.View {

    private lateinit var prefsManager: PrefsManager
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartPresenter: CartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        prefsManager = PrefsManager(this)
        cartPresenter = CartPresenter(this)
        cartPresenter.getCart(prefsManager.prefsUsername)
    }

    override fun onStart() {
        super.onStart()
        if (Constants.IS_CHANGED) {
            Constants.IS_CHANGED = false
            cartPresenter.getCart(prefsManager.prefsUsername)
            edtAgent.setText(Constants.AGENT_NAME)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constants.AGENT_NAME = ""
    }

    override fun initActivity() {
        supportActionBar!!.title = "Keranjang"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        cartAdapter = CartAdapter(this, arrayListOf()) { cart: Cart, position: Int ->
            cartPresenter.deleteItemCart(cart.kdKeranjang.toString())
        }
    }

    override fun initListener() {
        txvReset.visibility = View.GONE
        edtAgent.visibility = View.GONE

        rcvCart.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = cartAdapter
        }

        swipe.setOnRefreshListener {
            cartPresenter.getCart(prefsManager.prefsUsername)
        }

        btnAdd.setOnClickListener {
            startActivity(Intent(this, CartAddActivity::class.java))
        }

        txvReset.setOnClickListener {
            showDialog()
        }

        edtAgent.setOnClickListener {
            startActivity(Intent(this, AgentSearchActivity::class.java))
        }

        btnCheckout.setOnClickListener {
            if (cartAdapter.cartList.isNullOrEmpty()) {
                showMessage("Keranjang Kosong")
            } else {
                if (edtAgent.text.isNullOrEmpty()) {
                    edtAgent.error = "Tidak Boleh Kosong"
                } else {
                    cartPresenter.checkout(prefsManager.prefsUsername, Constants.AGENT_ID)
                }
            }
        }

    }

    override fun onLoadingCart(loading: Boolean) {
        when (loading) {
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResultCart(response: CartResponse) {
        val carts: List<Cart> = response.data!!
        showLog("" + carts)
        if (carts.isNullOrEmpty()) {
            txvReset.visibility = View.GONE
            edtAgent.visibility = View.GONE
        } else {
            cartAdapter.setCartList(carts)
            txvReset.visibility = View.VISIBLE
            edtAgent.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onResultDelete(response: CartUpdateResponse) {
        cartPresenter.getCart(prefsManager.prefsUsername)
        cartAdapter.removeAll()     // refresh
    }

    override fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi")
        builder.setMessage("Hapus semua item dalam keranjang?")
        builder.setPositiveButton("Hapus") { dialog, which ->
            cartPresenter.deleteCart(prefsManager.prefsUsername)
            dialog.dismiss()
        }
        builder.setNegativeButton("Batal") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    override fun onLoadingCheckout(loading: Boolean) {
        when (loading) {
            true -> {
                btnCheckout.isEnabled = false
                btnCheckout.text = "Memuat..."
            }
            false -> {
                btnCheckout.isEnabled = true
                btnCheckout.text = "Checkout"
            }
        }
    }

    override fun onResultCheckout(response: CheckoutResponse) {
        cartPresenter.getCart(prefsManager.prefsUsername)
        cartAdapter.removeAll()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}



























