package com.cuncis.sellingapp.ui.agent

import com.cuncis.sellingapp.data.model.AgentResponse

interface AgentContract {

    interface Presenter {
        fun getAgent()
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoadingAgent(isLoading: Boolean)
        fun onResultAgent(agentResponse: AgentResponse)
        fun showMessage(message: String)
    }

}