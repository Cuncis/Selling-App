package com.cuncis.sellingapp.ui.user

import com.cuncis.sellingapp.data.database.PrefsManager

class UserPresenter(val view: UserContract.View): UserContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun doLogin(prefsManager: PrefsManager) {
        if (prefsManager.prefsIsLogin) view.onResultLogin(prefsManager)
    }

    override fun doLogout(prefsManager: PrefsManager) {
        prefsManager.logout()
        view.showMessage("Logout")
        view.onResultLogout()
    }
}