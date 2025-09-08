package arzhie.zhafran.mobilemi2akotlin.list.recycler.linear.lat

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import arzhie.zhafran.mobilemi2akotlin.R
import arzhie.zhafran.mobilemi2akotlin.list.recycler.linear.BeritaAdapter
import arzhie.zhafran.mobilemi2akotlin.list.recycler.linear.BeritaModel
import arzhie.zhafran.mobilemi2akotlin.list.recycler.linear.DetailBeritaActivity

class ListsekolahActivity : AppCompatActivity() {
    private lateinit var rvSekolah: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listsekolah)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvSekolah = findViewById(R.id.rvSekolah)
    }
    override fun onStart() {
        super.onStart()
        setSekolah()
    }

    fun setSekolah(){
        val sekolah = listOf<SekolahModel>(
            SekolahModel(R.drawable.gambar4, "SMAN 1 Kubung", "1999", "0812345678", "Jl. Kubung Raya"),
            SekolahModel(R.drawable.gambar5, "SMAN 1 Padang", "1999", "0812345678", "Jl. Kubung Raya"),
            SekolahModel(R.drawable.gambar6, "SMAN 1 Solok", "1999", "0812345678", "Jl. Kubung Raya")

        )

        val SekolahAdapter = SekolahAdapter(sekolah, object : SekolahAdapter.OnAdapterListener{
            override fun onClick(result: SekolahModel) {
                val bundle = Bundle()
                bundle.putInt("Gambar", result.gambar)
                bundle.putString("Nama", result.nama)
                bundle.putString("Tahun", result.tahun)
                bundle.putString("Telepon", result.telepon)
                bundle.putString("Alamat", result.alamat)
                val intent = Intent(this@ListsekolahActivity,
                    DetailSekolahActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
        rvSekolah.adapter = SekolahAdapter
    }
}