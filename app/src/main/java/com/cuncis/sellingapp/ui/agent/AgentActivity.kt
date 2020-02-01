package com.cuncis.sellingapp.ui.agent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.model.agent.Agent
import com.cuncis.sellingapp.data.model.agent.AgentResponse
import com.cuncis.sellingapp.data.model.agent.AgentUpdateResponse
import com.cuncis.sellingapp.ui.agent.create.AgentCreateActivity
import com.cuncis.sellingapp.ui.agent.update.AgentUpdateActivity
import com.cuncis.sellingapp.util.Constants
import com.cuncis.sellingapp.util.Utils
import com.cuncis.sellingapp.util.Utils.Companion.showToast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.cuncis.sellingapp.util.Utils.Companion.setGlideImage
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.bottomsheet.BottomSheetDialog

import kotlinx.android.synthetic.main.activity_agent.*
import kotlinx.android.synthetic.main.content_agent.*
import kotlinx.android.synthetic.main.dialog_agent.*
import kotlinx.android.synthetic.main.dialog_agent.view.*

class AgentActivity : AppCompatActivity(), AgentContract.View, OnMapReadyCallback {

    private lateinit var presenter: AgentPresenter
    private lateinit var agentAdapter: AgentAdapter
    private lateinit var agent: Agent

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
        Utils.permissionMap(this, this)
    }

    override fun initListener() {
        agentAdapter = AgentAdapter(this, arrayListOf()) {
                dataAgent: Agent, position: Int, type: String ->

            agent = dataAgent

            when (type) {
                "update" -> startActivity(Intent(this, AgentUpdateActivity::class.java))
                "delete" -> showDialogDelete(dataAgent, position)
                "detail" -> showDialogDetail(dataAgent, position)
            }
        }
        rv_agent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = agentAdapter
        }

        swipe.setOnRefreshListener {
            presenter.getAgent()
        }

        fab.setOnClickListener {
            startActivity(Intent(this, AgentCreateActivity::class.java))
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

    override fun onResultDelete(agentResponse: AgentUpdateResponse) {
        showMessage("" + agentResponse.msg)
    }

    override fun showDialogDelete(dataAgent: Agent, position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi")
        builder.setMessage("Hapus ${agent.namaToko}?")
        builder.setPositiveButton("Hapus") { dialog, which ->
            presenter.deleteAgent(Constants.AGENT_ID)
            agentAdapter.removeAgent(position)
            dialog.dismiss()
        }
        builder.setNegativeButton("Batal") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    override fun showDialogDetail(dataAgent: Agent, position: Int) {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_agent, null)

        this.setGlideImage(view.imvStore, dataAgent.gambarToko!!, progressBar)

        view.txvNameStore.text = dataAgent.namaToko
        view.txvName.text = dataAgent.namaPemilik
        view.txvAddress.text = dataAgent.alamat

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        view.imvClose.setOnClickListener {
            supportFragmentManager.beginTransaction().remove(mapFragment).commit()
            dialog.dismiss()
        }

        dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()
    }

    override fun showMessage(message: String) {
        this.showToast(message)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLng = LatLng(agent.latitude!!.toDouble(), agent.longitude!!.toDouble())
        googleMap.addMarker(MarkerOptions().position(latLng).title(agent.namaToko))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
    }

}
