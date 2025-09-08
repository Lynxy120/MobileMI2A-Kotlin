package arzhie.zhafran.mobilemi2akotlin.list.recycler.linear.lat

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arzhie.zhafran.mobilemi2akotlin.R

class DetailSekolahActivity : AppCompatActivity() {
    private lateinit var imgSekolah: ImageView
    private lateinit var tvNama: TextView
    private lateinit var tvTahun: TextView
    private lateinit var tvTelepon: TextView
    private lateinit var tvAlamat: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_sekolah)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imgSekolah = findViewById(R.id.imgSekolah)
        tvNama = findViewById(R.id.tvNama)
        tvTahun = findViewById(R.id.tvTahun)
        tvTelepon = findViewById(R.id.tvTelepon)
        tvAlamat = findViewById(R.id.tvAlamat)
    }
    override fun onStart() {
        super.onStart()
        getData()
    }

    fun getData(){
        val bundle = intent.extras
        if (bundle != null) {
            imgSekolah.setImageResource(bundle.getInt("Gambar"))
            tvNama.text = bundle.getString("Nama")
            tvTahun.text = bundle.getString("Tahun")
            tvTelepon.text = bundle.getString("Telepon")
            tvAlamat.text = bundle.getString("Alamat")
        }
    }
}