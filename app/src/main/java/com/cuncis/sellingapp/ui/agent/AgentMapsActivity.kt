package com.cuncis.sellingapp.ui.agent

import android.content.ContentProviderClient
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    private lateinit var googlemap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private val marker = MarkerOptions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_maps)
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
        googlemap = map
        googlemap.uiSettings.isZoomControlsEnabled = true
        googlemap.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(this) {location ->
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                googlemap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))

                Constants.LATITUDE = location.latitude.toString()
                Constants.LONGITUDE = location.longitude.toString()

                showLog("Lat: ${Constants.LATITUDE}, Lng: ${Constants.LONGITUDE}")

                marker.position(currentLatLng)
                googlemap.addMarker(marker)

            }
        }

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        googlemap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googlemap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
