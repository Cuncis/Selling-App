package com.cuncis.sellingapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.database.PrefsManager
import com.cuncis.sellingapp.data.model.PegawaiResponse
import com.cuncis.sellingapp.util.Utils
import com.cuncis.sellingapp.util.Utils.Companion.showToast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var presenter: LoginPresenter
    private lateinit var prefsManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)
        prefsManager = PrefsManager(this)

    }

    override fun initActivity() {
        supportActionBar!!.title = "Masuk"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun initListener() {
        btnLogin.setOnClickListener {
            presenter.doLogin(edtUsername.text.toString().trim(), edtPassword.text.toString().trim())
        }
    }

    override fun onResult(responseLogin: PegawaiResponse) {
        presenter.setPrefs(prefsManager, responseLogin.pegawai!!)
        finish()
    }

    override fun showMessage(message: String) {
        this.showToast(message)
    }

    override fun onLoading(isLoading: Boolean) {
        when(isLoading) {
            true -> {
                progress.visibility = VISIBLE
                btnLogin.visibility = GONE
            }
            false -> {
                progress.visibility = GONE
                btnLogin.visibility = VISIBLE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
