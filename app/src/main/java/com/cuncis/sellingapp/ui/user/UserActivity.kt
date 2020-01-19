package com.cuncis.sellingapp.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.database.PrefsManager
import com.cuncis.sellingapp.util.Utils.Companion.showToast
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity(), UserContract.View {

    private lateinit var prefsManager: PrefsManager
    private lateinit var presenter: UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        prefsManager = PrefsManager(this)
        presenter = UserPresenter(this)
        presenter.doLogin(prefsManager)
    }

    override fun initActivity() {
        supportActionBar!!.hide()
    }

    override fun initListener() {
        btnBack.setOnClickListener {
            finish()
        }

        txvLogout.setOnClickListener {
            presenter.doLogout(prefsManager)
        }
    }

    override fun onResultLogin(prefsManager: PrefsManager) {
        txvUsername.text = prefsManager.prefsUsername
        txvName.text = prefsManager.prefsNamaPegawai
        txvAddress.text = prefsManager.prefsAlamat
        txvGender.text = prefsManager.prefsJk
    }

    override fun onResultLogout() {
        finish()
    }

    override fun showMessage(message: String) {
        this.showToast(message)
    }
}
