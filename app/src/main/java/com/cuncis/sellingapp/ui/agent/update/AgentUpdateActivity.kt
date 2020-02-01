package com.cuncis.sellingapp.ui.agent.update

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.model.agent.AgentDetailResponse
import com.cuncis.sellingapp.ui.agent.AgentMapsActivity
import com.cuncis.sellingapp.util.Constants
import com.cuncis.sellingapp.util.FileUtils
import com.lazday.poslaravel.util.GalleryHelper
import kotlinx.android.synthetic.main.activity_agent_create.*
import  com.cuncis.sellingapp.util.Utils.Companion.setGlideImage
import com.cuncis.sellingapp.util.Utils.Companion.showLog
import com.cuncis.sellingapp.data.model.agent.AgentCreateResponse

class AgentUpdateActivity : AppCompatActivity(), AgentUpdateContract.View {

    private var uriImage: Uri? = null
    private var pickImage = 1

    private lateinit var presenter: AgentUpdatePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_create)
        presenter = AgentUpdatePresenter(this)
        presenter.getDetail(Constants.AGENT_ID)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickImage && resultCode == Activity.RESULT_OK) {
            uriImage = data!!.data
            imvImage.setImageURI(uriImage)
        }
    }

    override fun onStart() {
        super.onStart()
        if (Constants.LATITUDE.isNotEmpty()) {
            edtLocation.setText("${Constants.LATITUDE}, ${Constants.LONGITUDE}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constants.LATITUDE = ""
        Constants.LONGITUDE = ""
    }

    override fun initActivity() {
        supportActionBar!!.title = "Agen Edit"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initListener() {
        edtLocation.setOnClickListener {
            startActivity(Intent(this, AgentMapsActivity::class.java))
        }

        imvImage.setOnClickListener {
            if (GalleryHelper.permissionGallery(this, this, pickImage)) {
                GalleryHelper.openGallery(this)
            }
        }

        btnSave.setOnClickListener {
            val nameStore = edtNameStore.text
            val nameOwner = edtNameOwner.text
            val address = edtAddress.text
            val location = edtLocation.text

            if (nameStore.isNullOrEmpty() || nameOwner.isNullOrEmpty() || address.isNullOrEmpty()
                || location.isNullOrEmpty()) {
                showMessage("Lengkapi data dengan benar")
            } else {
                presenter.updateAgent(Constants.AGENT_ID, nameStore.toString(), nameOwner.toString(), address.toString(),
                    Constants.LATITUDE, Constants.LONGITUDE, FileUtils.getFile(this, uriImage))
            }

            showLog("Name: $nameStore, Owner: $nameStore, Address: $address, Location: $location, Url: $uriImage")
        }
    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> {
                progress.visibility = View.VISIBLE
                btnSave.visibility = View.GONE
            }
            false -> {
                progress.visibility = View.GONE
                btnSave.visibility = View.VISIBLE
            }
        }
    }

    override fun onResultDetail(responseDetail: AgentDetailResponse) {
        val agent = responseDetail.data

        edtNameStore.setText(agent?.namaToko)
        edtNameOwner.setText(agent?.namaPemilik)
        edtAddress.setText(agent?.alamat)
        edtLocation.setText("${agent?.latitude}, ${agent?.longitude}")

        Constants.LATITUDE = agent!!.latitude!!
        Constants.LONGITUDE = agent.longitude!!

        this.setGlideImage(imvImage, agent.gambarToko!!, progress)
    }

    override fun onResultUpdate(responseUpdate: AgentCreateResponse) {
        showMessage("aa  " + responseUpdate.msg?.sukses?.get(0))
        finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
