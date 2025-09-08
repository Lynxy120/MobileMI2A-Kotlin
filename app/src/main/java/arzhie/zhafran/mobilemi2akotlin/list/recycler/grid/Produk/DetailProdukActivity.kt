package arzhie.zhafran.mobilemi2akotlin.list.recycler.grid.Produk

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arzhie.zhafran.mobilemi2akotlin.R

class DetailProdukActivity : AppCompatActivity() {
    private lateinit var imgProduk: ImageView
    private lateinit var tvNama: TextView
    private lateinit var tvHarga: TextView
    private lateinit var tvRating: TextView
    private lateinit var tvLokasi: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_produk)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imgProduk = findViewById(R.id.imgProduk)
        tvNama = findViewById(R.id.tvNama)
        tvHarga = findViewById(R.id.tvHarga)
        tvRating = findViewById(R.id.tvRating)
        tvLokasi = findViewById(R.id.tvLokasi)
    }
    override fun onStart() {
        super.onStart()
        getData()
    }
    fun getData() {
        val bundle = intent.extras
        if (bundle != null) {
            imgProduk.setImageResource(bundle.getInt("Gambar"))
            tvNama.text = bundle.getString("Nama")
            tvHarga.text = bundle.getString("Harga")
            tvRating.text = bundle.getFloat("Rating").toString()
            tvLokasi.text = bundle.getString("Lokasi")
        }
    }
}