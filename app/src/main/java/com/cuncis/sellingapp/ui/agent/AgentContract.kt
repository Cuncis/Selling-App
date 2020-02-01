package com.cuncis.sellingapp.ui.agent

import com.cuncis.sellingapp.data.model.agent.Agent
import com.cuncis.sellingapp.data.model.agent.AgentResponse
import com.cuncis.sellingapp.data.model.agent.AgentUpdateResponse

interface AgentContract {

    interface Presenter {
        fun getAgent()
        fun deleteAgent(kodeAgent: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoadingAgent(isLoading: Boolean)
        fun onResultAgent(agentResponse: AgentResponse)
        fun onResultDelete(agentResponse: AgentUpdateResponse)
        fun showDialogDelete(dataAgent: Agent, position: Int)
        fun showDialogDetail(dataAgent: Agent, position: Int)
        fun showMessage(message: String)
    }

}