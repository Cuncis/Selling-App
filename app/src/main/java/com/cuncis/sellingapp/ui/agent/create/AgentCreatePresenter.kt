package com.cuncis.sellingapp.ui.agent.create

import com.cuncis.sellingapp.network.ApiService
import com.cuncis.sellingapp.util.Utils.Companion.showLog
import com.cuncis.sellingapp.data.model.AgentCreateResponse
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class AgentCreatePresenter(val view: AgentCreateContract.View): AgentCreateContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun insertAgent(
        namaToko: String,
        namaPemilik: String,
        alamat: String,
        latitude: String,
        longitude: String,
        gambarToko: File
    ) {
        val requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), gambarToko)
        val multiparBody: MultipartBody.Part? = MultipartBody.Part.createFormData("gambar_toko", gambarToko.name, requestBody)

        view.onLoading(true)
        ApiService.theSellingApi.insertAgent(namaToko, namaPemilik, alamat, latitude, longitude, multiparBody!!)
            .enqueue(object : Callback<AgentCreateResponse> {
                override fun onFailure(call: Call<AgentCreateResponse>, t: Throwable) {
                    view.onLoading(false)
                    showLog("Error: " + t.message)
                    view.showMessage("" + t.message)
                }

                override fun onResponse(
                    call: Call<AgentCreateResponse>,
                    response: Response<AgentCreateResponse>
                ) {
                    view.onLoading(false)
                    val msgResponse = response.body()
                    if (response.isSuccessful) {
                        view.onResult("" + response.body()?.msg?.sukses?.get(0))
                        showLog("Sukses1: " + response.body()?.msg?.sukses?.get(0))
                        var str = ""
                        when {
                            msgResponse?.msg?.namaToko != null -> {
                                str = "Nama Toko"
                            }
                            msgResponse?.msg?.namaPemilik != null -> {
                                str = "Nama Pemilik"
                            }
                            msgResponse?.msg?.alamat != null -> {
                                str = "Alamat"
                            }
                            msgResponse?.msg?.latitude != null -> {
                                str = "latitude"
                            }
                            msgResponse?.msg?.longitude != null -> {
                                str = "Longitude"
                            }
                            msgResponse?.msg?.gambarToko != null -> {
                                str = "Gambar Toko"
                            }
                            msgResponse?.msg?.sukses != null -> {
                                str = "Sukses"
                            }
                        }
                        showLog("Sukses2: $str")
                    } else {
                        showLog("" + response.body())
//                        if (!response.body()!!.status!!) {
//                            if (msgResponse?.msg?.namaToko.toString() == )
//                            when (msgResponse?.msg) {
//
//                                msgResponse.msg.namaToko -> view.onResult(msgResponse.msgNamaToko!!.list[0])
//                            }
//                        }
                    }
                }

            })

    }

}