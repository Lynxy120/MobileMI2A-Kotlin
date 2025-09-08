package arzhie.zhafran.mobilemi2akotlin.list.recycler.linear

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import arzhie.zhafran.mobilemi2akotlin.R

class LinearRecyclerActivity : AppCompatActivity() {
    private lateinit var rvBerita: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_linear_recycler)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvBerita = findViewById(R.id.rvBerita)
    }

    override fun onStart() {
        super.onStart()
        setBerita()
    }

    fun setBerita(){
        val berita = listOf<BeritaModel>(
            BeritaModel(R.drawable.gambar1, "Judul Berita 1", "10 Oktober 2025", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata"),
            BeritaModel(R.drawable.gambar2, "Judul Berita 2", "9 Oktober 2025", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut la"),
            BeritaModel(R.drawable.gambar3, "Judul Berita 3", "8 Oktober 2025", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut la"),
        )

        val beritaAdapter = BeritaAdapter(berita, object : BeritaAdapter.OnAdapterListener{
            override fun onClick(result: BeritaModel) {
                val bundle = Bundle()
                bundle.putInt("gambar", result.gambar)
                bundle.putString("Judul", result.judul)
                bundle.putString("Tanggal", result.tanggal)
                bundle.putString("Isi", result.isi_berita)
                val intent = Intent(this@LinearRecyclerActivity,
                    DetailBeritaActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
        rvBerita.adapter = beritaAdapter
    }
}