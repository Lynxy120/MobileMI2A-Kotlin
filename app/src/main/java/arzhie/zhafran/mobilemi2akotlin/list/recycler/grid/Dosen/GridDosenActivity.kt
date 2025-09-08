package arzhie.zhafran.mobilemi2akotlin.list.recycler.grid.Dosen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import arzhie.zhafran.mobilemi2akotlin.R

class GridDosenActivity : AppCompatActivity() {
    private lateinit var recGrid: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_grid_dosen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recGrid = findViewById(R.id.recGridDosen)
    }

    override fun onStart() {
        super.onStart()
        setDosen()
    }

    private fun setDosen() {
        val listDosen = listOf(
            DosenModel(R.drawable.gambar1, "Roni Putra, M.Kom", "NIDN: 1029384", "Rekayasa Perangkat Lunak"),
            DosenModel(R.drawable.gambar2, "Prof. Dr. Shino Aburame, M.T", "NIDN: 2938475", "Ahli Serangga"),
            DosenModel(R.drawable.gambar3, "Dr. Gintoki Sakata, M.Kom", "NIDN: 3847562", "Aktor"),
            DosenModel(R.drawable.gambar4, "Prof. Dr. KuroSensei, S.Kom", "NIDN: 4758693", "Wali Kelas")
        )

        val dosenAdapter = DosenAdapter(listDosen, object : DosenAdapter.OnAdapterListener {
            override fun onClick(result: DosenModel) {
                val bundle = Bundle()
                bundle.putInt("Gambar", result.gambar)
                bundle.putString("Nama", result.nama)
                bundle.putString("Nidn", result.nidn)
                bundle.putString("Bidang", result.bidang)

                val intent = Intent(this@GridDosenActivity, DetailDosenActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })

        recGrid.adapter = dosenAdapter
    }
}