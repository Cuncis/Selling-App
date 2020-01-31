package com.cuncis.sellingapp.ui.agent.create

import java.io.File

interface AgentCreateContract {

    interface Presenter {
        fun insertAgent(namaToko: String, namaPemilik: String, alamat: String, latitude: String,
                        longitude: String, gambarToko: File)
    }

    interface View{
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
//        fun onResult(response: AgentUpdateResponse)
        fun onResult(response: String)
        fun showMessage(message: String)
    }

}