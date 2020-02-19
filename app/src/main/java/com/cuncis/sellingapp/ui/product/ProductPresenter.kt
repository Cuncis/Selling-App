package com.cuncis.sellingapp.ui.product

import com.cuncis.sellingapp.data.model.category.KategoriResponse
import com.cuncis.sellingapp.data.model.product.ProdukResponse
import com.cuncis.sellingapp.network.ApiService
import com.cuncis.sellingapp.util.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductPresenter(val view: ProductContract.View): ProductContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getCategory() {
        view.onLoading(true)
        ApiService.theSellingApi.getCategory()
            .enqueue(object : Callback<KategoriResponse> {
                override fun onResponse(call: Call<KategoriResponse>, response: Response<KategoriResponse>) {
                    view.onLoading(false)
                    if (response.isSuccessful) {
                        val categoryResponse = response.body()
                        view.onResultCategory(categoryResponse!!)
                    } else {
                        Utils.showLog("Error1: " + response.message())
                    }
                }
                override fun onFailure(call: Call<KategoriResponse>, t: Throwable) {
                    view.onLoading(false)
                    Utils.showLog("Error2: " + t.message)
                }
            })
    }

    override fun getProduct(kdKategori: String) {
        view.onLoading(true)
        ApiService.theSellingApi.getPrductByCategory(kdKategori)
            .enqueue(object : Callback<ProdukResponse> {
                override fun onResponse(call: Call<ProdukResponse>, response: Response<ProdukResponse>) {
                    view.onLoading(false)
                    if (response.isSuccessful) {
                        val productResponse = response.body()
                        view.onResultProduct(productResponse!!)
                    } else {
                        Utils.showLog("Error1: " + response.message())
                    }
                }
                override fun onFailure(call: Call<ProdukResponse>, t: Throwable) {
                    view.onLoading(false)
                    Utils.showLog("Error2: " + t.message)
                }
            })
    }
}