package com.chaity.android.easy.move.ui.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chaity.android.easy.move.BuildConfig.BUNDLE_KEY_DELIVERY
import com.chaity.android.easy.move.R
import com.chaity.android.easy.move.model.Delivery
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.delivery_view_item.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var deliveryItem: Delivery

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val ab = supportActionBar
        ab!!.setHomeButtonEnabled(true)
        ab.setDisplayHomeAsUpEnabled(true)
        getIntentData()



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)



    }

    private fun getIntentData() {
        deliveryItem = intent.extras.getParcelable<Delivery>(BUNDLE_KEY_DELIVERY)

    }


    // Support Action Bar Back navigation
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        tv_del.text=deliveryItem.description
        Picasso.get()
                .load(deliveryItem.imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_eye)
                .fit()
                .into(iv_del)

        // Add a marker using specified lat lang  and move the camera
        val sydney = LatLng(deliveryItem.location.lat,deliveryItem.location.lng)
        mMap.addMarker(MarkerOptions().position(sydney).title(deliveryItem.description))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        //To just change the zoom value to any desired value between minimum value=2.0 and maximum value=21.0.
        //
        //The API warns that not all locations have tiles at values at or near maximum zoom.
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
    }
}
