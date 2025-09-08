package arzhie.zhafran.mobilemi2akotlin.map

import android.content.pm.PackageManager
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import arzhie.zhafran.mobilemi2akotlin.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import arzhie.zhafran.mobilemi2akotlin.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fabStyleMap: FloatingActionButton
    private lateinit var fabStyleMapStandard: FloatingActionButton
    private lateinit var fabStyleMapSatelite: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fabStyleMap = findViewById(R.id.fabStyleMap)
        fabStyleMapStandard = findViewById(R.id.fabStyleMapStandard)
        fabStyleMapSatelite = findViewById(R.id.fabStyleMapSatelite)

    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val zoomMap = 12f

        // Add a marker in Sydney and move the camera
        val PNP = LatLng(-0.9467766922485451, 100.3765901264007)
        mMap.addMarker(MarkerOptions()
            .position(PNP)
            .title("Politeknik Negeri Padang")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.rs))
        )

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PNP,zoomMap))

        //tambahkan data Map
        val listMap = listOf(
            MapModel(-0.9446239475272155, 100.37603178080387,
                "Sutomo hotel",R.drawable.rs),
            MapModel(-0.9223459814823097, 100.35090185917672,
                "pangeran hotel",R.drawable.hotel),
            MapModel(-0.928434717201541, 100.37018449193167,
                "hotel ibis",R.drawable.rs),
            MapModel(-0.9440293669466061, 100.35727685209025,
                "fav hotel",R.drawable.hotel),
        )
        listMap.forEach {
            mMap.addMarker(MarkerOptions()
                .position(LatLng(it.Lat, it.Lng))
                .title(it.Title)
                .icon(BitmapDescriptorFactory.fromResource(it.Gambar)
                ))
        }
        setStyleMapAubergine()
        setTypeSatelite()
        enableLocation()
        setStyleMapStandard()
    }

    private fun enableLocation(){
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)  == PackageManager.PERMISSION_GRANTED){
            mMap.isMyLocationEnabled = true
        }else{
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1)
        }
    }
    private fun setTypeSatelite() {
        fabStyleMapSatelite.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        }
    }
    private fun setStyleMapAubergine(){
        try {
            fabStyleMap.setOnClickListener {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.my_style_aubergine))
            }
        }catch (e: Resources.NotFoundException){
            //Toast.makeText(this, Toast)
            Log.e("Error Style",e.toString())
        }
    }
    private fun setStyleMapStandard(){
        fabStyleMapStandard.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.map_style_standard))
        }
    }
}