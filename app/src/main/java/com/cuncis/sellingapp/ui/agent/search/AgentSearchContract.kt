package com.cuncis.sellingapp.ui.agent.search

import com.cuncis.sellingapp.data.model.agent.AgentDetailResponse
import com.cuncis.sellingapp.data.model.agent.AgentResponse

interface AgentSearchContract {

    interface Presenter {
        fun getAgent()
        fun searchAgent(keyword: String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoadingAgent(loading: Boolean)
        fun onResultAgent(response: AgentResponse)
        fun showMessage(message: String)
    }

}