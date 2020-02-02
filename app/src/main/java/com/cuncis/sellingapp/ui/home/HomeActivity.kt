package com.cuncis.sellingapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.database.PrefsManager
import com.cuncis.sellingapp.ui.agent.AgentActivity
import com.cuncis.sellingapp.ui.login.LoginActivity
import com.cuncis.sellingapp.ui.transaction.TransactionActivity
import com.cuncis.sellingapp.ui.user.UserActivity
import com.cuncis.sellingapp.util.Utils.Companion.showToast
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeContract.View {

    private lateinit var prefsManager: PrefsManager
    private lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        prefsManager = PrefsManager(this)
        presenter = HomePresenter(this)
    }

    override fun onStart() {
        super.onStart()
        when (prefsManager.prefsIsLogin) {
            true -> {
                crvUser.visibility = VISIBLE
                btnLogin.visibility = GONE
            }
            false -> {
                crvUser.visibility = GONE
                btnLogin.visibility = VISIBLE
            }
        }
    }

    override fun initListener() {
        crvTransaction.setOnClickListener {
            if (prefsManager.prefsIsLogin) {
                startActivity(Intent(this, TransactionActivity::class.java))
            } else {
                showMessage("Anda Belum Login")
            }
        }

        crvAgent.setOnClickListener {
            if (prefsManager.prefsIsLogin) {
                startActivity(Intent(this, AgentActivity::class.java))
            } else {
                showMessage("Anda Belum Login")
            }
        }
        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        crvUser.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    override fun showMessage(message: String) {
        this.showToast(message)
    }
}
