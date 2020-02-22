package com.cuncis.sellingapp.ui.agent.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.model.agent.Agent
import com.cuncis.sellingapp.data.model.agent.AgentDetail
import com.cuncis.sellingapp.data.model.agent.AgentDetailResponse
import com.cuncis.sellingapp.data.model.agent.AgentResponse
import com.cuncis.sellingapp.util.Constants
import com.cuncis.sellingapp.util.Utils.Companion.showLog
import kotlinx.android.synthetic.main.activity_agent_search.*
import kotlinx.android.synthetic.main.item_agent_search.*

class AgentSearchActivity : AppCompatActivity(), AgentSearchContract.View {

    private lateinit var presenter: AgentSearchPresenter
    private lateinit var agentadapter: AgentSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_search)

        presenter = AgentSearchPresenter(this)
        presenter.getAgent()
    }

    override fun onStart() {
        super.onStart()
        presenter.getAgent()
    }

    override fun initActivity() {
        supportActionBar!!.title = "Pilih Agent"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initListener() {
        agentadapter = AgentSearchAdapter(this, arrayListOf()) {
                detail: Agent, _: Int ->
            Constants.AGENT_ID = detail.kdAgen!!.toLong()
            Constants.AGENT_NAME = detail.namaToko!!
            Constants.IS_CHANGED = true
            finish()
        }

        edtSearch.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                presenter.searchAgent( edtSearch.text.toString() )
                true
            } else {
                false
            }
        }

        rcvAgent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = agentadapter
        }

        swipe.setOnRefreshListener {
            presenter.getAgent()
        }
    }

    override fun onLoadingAgent(loading: Boolean) {
        when (loading) {
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResultAgent(response: AgentResponse) {
        showLog("a - " + response.data)
        val data: List<Agent> = response.data!!
        agentadapter.setAgentList(data)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
