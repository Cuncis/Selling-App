package com.cuncis.sellingapp.ui.agent

import android.content.ContentProviderClient
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.util.Constants
import com.cuncis.sellingapp.util.Utils.Companion.showLog
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class AgentMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private val marker = MarkerOptions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_maps)
        supportActionBar!!.title = "Lokasi"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(this) {location ->
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))

                Constants.LATITUDE = location.latitude.toString()
                Constants.LONGITUDE = location.longitude.toString()

                showLog("Lat: ${Constants.LATITUDE}, Lng: ${Constants.LONGITUDE}")

                marker.position(currentLatLng)
                googleMap.addMarker(marker)

            }
        }

        googleMap.setOnMapClickListener {latLng ->
            marker.position(latLng)
            marker.title(latLng.latitude.toString() + " : " + latLng.longitude.toString())

            Constants.LATITUDE = latLng.latitude.toString()
            Constants.LONGITUDE = latLng.longitude.toString()

            showLog("Lat: ${Constants.LATITUDE}, Lng: ${Constants.LONGITUDE}")

            googleMap.clear()
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
            googleMap.addMarker(marker)
        }
    }

    // just for display the menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_maps, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}


















