package com.cuncis.sellingapp.ui.agent

import com.cuncis.sellingapp.data.model.AgentResponse
import com.cuncis.sellingapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgentPresenter(val view: AgentContract.View): AgentContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getAgent() {
        view.onLoadingAgent(true)
        ApiService.theSellingApi.getAgent()
            .enqueue(object : Callback<AgentResponse> {
                override fun onResponse(call: Call<AgentResponse>, response: Response<AgentResponse>) {
                    view.onLoadingAgent(false)
                    if (response.isSuccessful) {
                        val responseAgent = response.body()
                        view.onResultAgent(responseAgent!!)
                    } else {
                        view.showMessage("" + response.message())
                    }
                }
                override fun onFailure(call: Call<AgentResponse>, t: Throwable) {
                    view.onLoadingAgent(false)
                    view.showMessage("" + t.message)
                }
            })
    }
}