package com.cuncis.sellingapp.ui.cart.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.database.PrefsManager
import com.cuncis.sellingapp.data.model.cart.CartUpdateResponse
import com.cuncis.sellingapp.ui.product.ProductActivity
import com.cuncis.sellingapp.util.Constants
import kotlinx.android.synthetic.main.activity_cart_add.*

class CartAddActivity : AppCompatActivity(), CartAddContract.View {

    private lateinit var presenter: CartAddPresenter
    private lateinit var prefsManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_add)
        prefsManager = PrefsManager(this)
        presenter = CartAddPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        if (Constants.IS_CHANGED) {
            Constants.IS_CHANGED = false
            edtProduct.setText(Constants.PRODUCT_NAME)
            txvQty.visibility = View.VISIBLE
            npQuantity.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constants.PRODUCT_ID = 0
        Constants.PRODUCT_NAME = ""
        Constants.PRODUCT_QTY = 0
    }

    override fun initActivity() {
        supportActionBar!!.title = "Tambah Produk"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        txvQty.visibility = View.GONE
        npQuantity.visibility = View.GONE
    }

    override fun initListener() {
        edtProduct.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }

        npQuantity.minValue = 1
        npQuantity.maxValue = 25
        npQuantity.wrapSelectorWheel = true
        npQuantity.setOnValueChangedListener { picker, oldVal, newVal ->
            Constants.PRODUCT_QTY = newVal.toLong()
        }

        btnSubmit.setOnClickListener {
            if (Constants.PRODUCT_ID > 0) {
                Constants.PRODUCT_QTY = if (Constants.PRODUCT_QTY > 0) Constants.PRODUCT_QTY else 1
                presenter.addCart(
                    prefsManager.prefsUsername, Constants.PRODUCT_ID, Constants.PRODUCT_QTY
                )
            } else {
                edtProduct.error = "Tidak boleh kosong"
            }
        }
    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> {
                progress.visibility = View.VISIBLE
                btnSubmit.visibility = View.GONE
            }
            false -> {
                progress.visibility = View.GONE
                btnSubmit.visibility = View.VISIBLE
            }
        }
    }

    override fun onResult(response: CartUpdateResponse) {
        if (response.status!!) {
            Constants.IS_CHANGED = true
            finish()
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
