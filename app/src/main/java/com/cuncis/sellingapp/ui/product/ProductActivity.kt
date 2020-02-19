package com.cuncis.sellingapp.ui.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.model.category.Kategori
import com.cuncis.sellingapp.data.model.category.KategoriResponse
import com.cuncis.sellingapp.data.model.product.Produk
import com.cuncis.sellingapp.data.model.product.ProdukResponse
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity(), ProductContract.View {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var presenter: ProductPresenter
    private var kdKategori: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        productAdapter = ProductAdapter(this, arrayListOf())
        presenter = ProductPresenter(this)
        presenter.getCategory()
    }

    override fun initActivity() {
        supportActionBar!!.hide()

        categoryAdapter = CategoryAdapter(this, arrayListOf()) {
            category: Kategori, position: Int ->
            kdKategori = category.kdKategori!!
            presenter.getProduct(kdKategori.toString())
        }
    }

    override fun initListener() {
        rcvCategory.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = categoryAdapter
        }

        rcvProduct.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = productAdapter
        }

        swipe.setOnRefreshListener {
            when (View.VISIBLE) {
                rcvCategory.visibility -> presenter.getCategory()
                rcvProduct.visibility -> presenter.getProduct(kdKategori.toString())
            }
        }

        imvClose.setOnClickListener {
            when (View.VISIBLE) {
                rcvCategory.visibility -> finish()
                rcvProduct.visibility -> {
                    rcvProduct.visibility = View.GONE
                    rcvCategory.visibility = View.VISIBLE
                    txvCategory.text = "Pilih Kategori"
                }
            }
        }
    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> {
                swipe.isRefreshing = true
                rcvCategory.visibility = View.GONE
                rcvProduct.visibility = View.GONE
            }
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResultCategory(response: KategoriResponse) {
        rcvCategory.visibility = View.VISIBLE
        val categoryList: List<Kategori> = response.data!!
        categoryAdapter.setData(categoryList)
        txvCategory.text = "Pilih Kategori"
    }

    override fun onResultProduct(response: ProdukResponse) {
        rcvProduct.visibility = View.VISIBLE
        val productList: List<Produk> = response.data!!
        productAdapter.setData(productList)
        txvCategory.text = productList[0].kategori
    }

    override fun onBackPressed() {
        when (View.VISIBLE) {
            rcvCategory.visibility -> finish()
            rcvProduct.visibility -> {
                rcvProduct.visibility = View.GONE
                rcvCategory.visibility = View.VISIBLE
                txvCategory.text = "Pilih Kategori"
            }
        }
    }
}
