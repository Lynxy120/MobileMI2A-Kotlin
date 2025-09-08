package arzhie.zhafran.mobilemi2akotlin.list.recycler.linear

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arzhie.zhafran.mobilemi2akotlin.R

class DetailBeritaActivity : AppCompatActivity() {
    private lateinit var imgBerita: ImageView
    private lateinit var tvJudul: TextView
    private lateinit var tvTgl: TextView
    private lateinit var tvIsi: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_berita)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imgBerita = findViewById(R.id.imgBerita)
        tvJudul = findViewById(R.id.tvJudul)
        tvTgl = findViewById(R.id.tvTgl)
        tvIsi = findViewById(R.id.tvIsi)
    }
    override fun onStart() {
        super.onStart()
        getData()
    }

    fun getData(){
        val bundle = intent.extras
        if (bundle != null) {
            imgBerita.setImageResource(bundle.getInt("gambar"))
            tvJudul.text = bundle.getString("Judul")
            tvTgl.text = bundle.getString("Tanggal")
            tvIsi.text = bundle.getString("Isi")
        }
    }
}