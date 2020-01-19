package com.cuncis.sellingapp.ui.login

import com.cuncis.sellingapp.data.database.PrefsManager
import com.cuncis.sellingapp.data.model.Pegawai
import com.cuncis.sellingapp.data.model.PegawaiResponse
import com.cuncis.sellingapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val view: LoginContract.View): LoginContract.Presenter {
    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun doLogin(username: String, password: String) {
        view.onLoading(true)
        ApiService.theSellingApi.loginUser(username, password)
            .enqueue(object : Callback<PegawaiResponse> {
            override fun onResponse(call: Call<PegawaiResponse>, response: Response<PegawaiResponse>) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseLogin = response.body()
                    view.showMessage("Success Login as ${responseLogin?.pegawai?.namaPegawai}")
                    if (responseLogin!!.status!!) {
                        view.onResult(responseLogin)
                    }
                    view.onResult(response.body()!!)
                } else {
                    view.showMessage("" + response.message())
                }
            }
            override fun onFailure(call: Call<PegawaiResponse>, t: Throwable) {
                view.onLoading(false)
                view.showMessage("" + t.message)
            }
        })
    }

    override fun setPrefs(prefsManager: PrefsManager, loginData: Pegawai) {
        prefsManager.prefsIsLogin = true
        prefsManager.prefsUsername = loginData.username!!
        prefsManager.prefsNamaPegawai = loginData.namaPegawai!!
        prefsManager.prefsJk = loginData.jk!!
        prefsManager.prefsAlamat = loginData.alamat!!
        prefsManager.prefsIsAktif = loginData.isAktif!!
    }

}