package com.cuncis.sellingapp.ui.agent.create

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.ui.agent.AgentMapsActivity
import com.cuncis.sellingapp.util.Utils
import kotlinx.android.synthetic.main.activity_agent_create.*

class AgentCreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_create)
        supportActionBar!!.title = "Agen Baru"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        edtLocation.setOnClickListener {
            startActivity(Intent(this, AgentMapsActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
