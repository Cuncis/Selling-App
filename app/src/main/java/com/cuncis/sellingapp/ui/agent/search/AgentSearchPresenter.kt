package com.cuncis.sellingapp.ui.agent.search

import com.cuncis.sellingapp.data.model.agent.AgentDetailResponse
import com.cuncis.sellingapp.data.model.agent.AgentResponse
import com.cuncis.sellingapp.network.ApiService
import com.cuncis.sellingapp.util.Utils.Companion.showLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgentSearchPresenter(val view: AgentSearchContract.View): AgentSearchContract.Presenter {

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
                        showLog("Agent Data -> " + response.body())
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

    override fun searchAgent(keyword: String) {
        view.onLoadingAgent(true)
        ApiService.theSellingApi.searchAgent(keyword)
            .enqueue(object : Callback<AgentResponse> {
                override fun onResponse(call: Call<AgentResponse>, response: Response<AgentResponse>) {
                    view.onLoadingAgent(false)
                    if (response.isSuccessful) {
                        val responseAgent = response.body()
                        view.onResultAgent(responseAgent!!)
                        showLog("Search Data -> " + response.body())
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