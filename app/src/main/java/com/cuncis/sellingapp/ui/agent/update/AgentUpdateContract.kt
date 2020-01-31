package com.cuncis.sellingapp.ui.agent.update

import com.cuncis.sellingapp.data.model.AgentDetailResponse
import com.cuncis.sellingapp.x.AgentUpdateResponse
import java.io.File

interface AgentUpdateContract {

    interface Presenter {
        fun getDetail(kodeAgen: Long)
        fun updateAgent(kodeAgen: Long, namaToko: String, namaPemilik: String, alamat: String,
                        latitude: String, longitude: String, gambarToko: File?)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResultDetail(responseDetail: AgentDetailResponse)
        fun onResultUpdate(responseUpdate: AgentUpdateResponse)
        fun showMessage(message: String)
    }

}