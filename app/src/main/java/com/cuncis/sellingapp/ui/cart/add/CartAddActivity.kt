package com.cuncis.sellingapp.ui.cart.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.ui.product.ProductActivity
import kotlinx.android.synthetic.main.activity_cart_add.*

class CartAddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_add)

        edtProduct.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }
    }
}
