package com.cuncis.sellingapp.ui.agent.update

import android.widget.Toast
import com.cuncis.sellingapp.data.model.AgentDetailResponse
import com.cuncis.sellingapp.data.model.AgentUpdateResponse
import com.cuncis.sellingapp.network.ApiService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class AgentUpdatePresenter (val view: AgentUpdateContract.View): AgentUpdateContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getDetail(kodeAgen: Long) {
        view.onLoading(true)
        ApiService.theSellingApi.getAgentDetail(kodeAgen)
            .enqueue(object : Callback<AgentDetailResponse> {
                override fun onFailure(call: Call<AgentDetailResponse>, t: Throwable) {
                    view.onLoading(false)
                    view.showMessage("" + t.message)
                }

                override fun onResponse(
                    call: Call<AgentDetailResponse>,
                    response: Response<AgentDetailResponse>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful) {
                        view.onResultDetail(response.body()!!)
                    }
                }
            })
    }

    override fun updateAgent(
        kodeAgen: Long,
        namaToko: String,
        namaPemilik: String,
        alamat: String,
        latitude: String,
        longitude: String,
        gambarToko: File?
    ) {
        val requestBody: RequestBody
        val multipartBody: MultipartBody.Part

        if (gambarToko != null) {
            requestBody = RequestBody.create(MediaType.parse("image/*"), gambarToko)
            multipartBody = MultipartBody.Part.createFormData("gambar_toko",
                gambarToko.name, requestBody)
        } else {
            requestBody = RequestBody.create(MediaType.parse("image/*"), "")
            multipartBody = MultipartBody.Part.createFormData("gambar_toko",
                "", requestBody)
        }

        view.onLoading(true)
        ApiService.theSellingApi.updateAgent(kodeAgen, namaToko, namaPemilik, alamat, latitude, longitude, multipartBody, "PUT")
            .enqueue(object : Callback<AgentUpdateResponse> {
                override fun onFailure(call: Call<AgentUpdateResponse>, t: Throwable) {
                    view.onLoading(false)
                    view.showMessage("" + t.message)
                }

                override fun onResponse(
                    call: Call<AgentUpdateResponse>,
                    response: Response<AgentUpdateResponse>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful) {
                        view.onResultUpdate(response.body()!!)
                    }
                }

            })
    }
}

















