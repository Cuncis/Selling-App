package com.cuncis.sellingapp.ui.agent.create

import android.widget.Toast
import com.cuncis.sellingapp.data.model.AgentUpdateResponse
import com.cuncis.sellingapp.network.ApiService
import com.cuncis.sellingapp.ui.agent.AgentContract
import com.cuncis.sellingapp.util.Utils.Companion.showLog
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
            .enqueue(object : Callback<AgentUpdateResponse> {
                override fun onFailure(call: Call<AgentUpdateResponse>, t: Throwable) {
                    view.onLoading(false)
                    showLog("Error: " + t.message)
                    view.showMessage("" + t.message)
                }

                override fun onResponse(
                    call: Call<AgentUpdateResponse>,
                    response: Response<AgentUpdateResponse>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful) {
                        view.onResult(response.body()!!)
                    } else if (response.code() == 400) {
                        if (response.body()?.status == false) {
                            showLog("Error1: " + response.body()!!.msg)
                        }
                        showLog("Error1: $response")
                    }
                }

            })

    }

}