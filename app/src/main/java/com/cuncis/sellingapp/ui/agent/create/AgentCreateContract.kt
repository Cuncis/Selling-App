package com.cuncis.sellingapp.ui.agent.create

import com.cuncis.sellingapp.data.model.AgentUpdateResponse
import java.io.File

interface AgentCreateContract {

    interface Presenter {
        fun insertAgent(namaToko: String, namaPemilik: String, alamat: String, latitude: String,
                        longitude: String, gambarToko: File)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(response: AgentUpdateResponse)
        fun showMessage(message: String)
    }

}