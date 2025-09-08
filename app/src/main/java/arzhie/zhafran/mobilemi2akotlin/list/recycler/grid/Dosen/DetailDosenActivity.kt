package arzhie.zhafran.mobilemi2akotlin.list.recycler.grid.Dosen

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arzhie.zhafran.mobilemi2akotlin.R

class DetailDosenActivity : AppCompatActivity() {
    private lateinit var imgDosen: ImageView
    private lateinit var tvNama: TextView
    private lateinit var tvNidn: TextView
    private lateinit var tvBidang: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_dosen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imgDosen = findViewById(R.id.imgDetDosen)
        tvNama = findViewById(R.id.tvDetNama)
        tvNidn = findViewById(R.id.tvDetNidn)
        tvBidang = findViewById(R.id.tvDetBidang)
    }

    override fun onStart() {
        super.onStart()
        getData()
    }

    fun getData() {
        val bundle = intent.extras
        if (bundle != null) {
            imgDosen.setImageResource(bundle.getInt("Gambar"))
            tvNama.text = bundle.getString("Nama")
            tvNidn.text = bundle.getString("Nidn")
            tvBidang.text = bundle.getString("Bidang")
        }
    }
}