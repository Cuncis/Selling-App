package com.cuncis.sellingapp.ui.agent

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.model.Agent
import com.cuncis.sellingapp.data.model.AgentResponse
import com.cuncis.sellingapp.util.Utils.Companion.showToast

import kotlinx.android.synthetic.main.activity_agent.*
import kotlinx.android.synthetic.main.content_agent.*

class AgentActivity : AppCompatActivity(), AgentContract.View {

    private lateinit var presenter: AgentPresenter
    private lateinit var agentAdapter: AgentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent)
        setSupportActionBar(toolbar)
        presenter = AgentPresenter(this)

    }

    override fun onStart() {
        super.onStart()
        presenter.getAgent()
    }

    override fun initActivity() {
        supportActionBar!!.title = "Agen"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initListener() {
        agentAdapter = AgentAdapter(this, arrayListOf())
        rv_agent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = agentAdapter
        }

        swipe.setOnRefreshListener {
            presenter.getAgent()
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onLoadingAgent(isLoading: Boolean) {
        when(isLoading) {
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResultAgent(agentResponse: AgentResponse) {
        val dataAgent: List<Agent> = agentResponse.data!!
        agentAdapter.setAgentList(dataAgent)
    }

    override fun showMessage(message: String) {
        this.showToast(message)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

}
