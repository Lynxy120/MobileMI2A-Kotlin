package arzhie.zhafran.mobilemi2akotlin.list.recycler.grid.Produk

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import arzhie.zhafran.mobilemi2akotlin.R

class GridRecyclerActivity : AppCompatActivity() {
    private lateinit var recGrid: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_grid_recycler)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recGrid = findViewById(R.id.recGrid)
    }
    override fun onStart() {
        super.onStart()
        setProduk()
    }

    private fun setProduk() {
        val produk = listOf<ProdukModel>(
            ProdukModel(gambar_produk = R.drawable.gambar1, "Cerydra", "Rp30.000.000k", 4.9F, "Amphoreus"),
            ProdukModel(gambar_produk = R.drawable.gambar2, "FireFly", "Rp25.000.000k", 4.5F, "Penacony"),
            ProdukModel(gambar_produk = R.drawable.gambar3, "Furina", "Rp45.000.000k", 5.0F, "Fontaine"),
            ProdukModel(gambar_produk = R.drawable.gambar6, "Sekolah", "Rp10M", 3.0F, "Indonesia")
        )

        val produkAdapter = ProdukAdapter(produk, object : ProdukAdapter.OnAdapterListener {
            override fun onClick(result: ProdukModel) {
                val bundle = Bundle()
                bundle.putInt("Gambar", result.gambar_produk)
                bundle.putString("Nama", result.nama_produk)
                bundle.putString("Harga", result.harga)
                bundle.putFloat("Rating", result.rating)
                bundle.putString("Lokasi", result.lokasi)
                val intent = Intent(this@GridRecyclerActivity,
                    DetailProdukActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })

        recGrid.adapter = produkAdapter
    }
}