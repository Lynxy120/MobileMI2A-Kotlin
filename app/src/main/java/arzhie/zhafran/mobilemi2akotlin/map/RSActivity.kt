package arzhie.zhafran.mobilemi2akotlin.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import arzhie.zhafran.mobilemi2akotlin.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import arzhie.zhafran.mobilemi2akotlin.databinding.ActivityRsBinding

class RSActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityRsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
        val zoom = 13f

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-0.9366875572768141, 100.38617103967361)
        mMap.addMarker(MarkerOptions().position(sydney).title("Hannah Guest House Syariah"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoom))

        val listMap = listOf(
            RSModel(-0.9201000162578961, 100.45695733479091, "Rumah Sakit Universitas Andalas"),
            RSModel(-0.9393235589523844, 100.39962243749328, "Semen Padang Hospital"),
            RSModel(-0.9146075563676417, 100.35979699984941, "Rumah Sakit Hermina"),
            RSModel(-0.9185552627662628, 100.36649179324644, "Rumah Sakit Islam Ibnu Sina Padang"),
            RSModel(-0.9410399415824803, 100.36632013187729, "RSUP M. Djamil Padang"),
            RSModel(-0.9458702016517694, 100.36359756713414, "Rumah Sakit Umum Aisyiyah"),
            RSModel(-0.9462992965597924, 100.37690132453818, "Rumah Sakit Ibu dan Anak Siti Hawa")
        )

        listMap.forEach {
            mMap.addMarker(MarkerOptions()
                .position(LatLng(it.lang, it.Long))
                .title((it.Nama))
            )
        }
    }
}