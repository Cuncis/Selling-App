package com.cuncis.sellingapp.ui.login

import com.cuncis.sellingapp.data.database.PrefsManager
import com.cuncis.sellingapp.data.model.Pegawai
import com.cuncis.sellingapp.data.model.PegawaiResponse

interface LoginContract {

    interface Presenter {
        fun doLogin(username: String, password: String)
        fun setPrefs(prefsManager: PrefsManager, loginData: Pegawai)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(isLoading: Boolean)
        fun onResult(responseLogin: PegawaiResponse)
        fun showMessage(message: String)
    }

}