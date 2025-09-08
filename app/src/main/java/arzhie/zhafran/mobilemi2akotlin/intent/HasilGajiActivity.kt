package arzhie.zhafran.mobilemi2akotlin.intent

import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arzhie.zhafran.mobilemi2akotlin.R

class HasilGajiActivity : AppCompatActivity() {
    private lateinit var tvNIP: TextView
    private lateinit var tvNama: TextView
    private lateinit var tvTgl: TextView
    private lateinit var tvJam: TextView
    private lateinit var tvStatus: TextView
    private lateinit var tvAnak: TextView
    private lateinit var tvGol: TextView
    private lateinit var tablePendapatan: TableLayout
    private lateinit var tvGaji: TextView
    private lateinit var btnKembali: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hasil_gaji)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
        getData()
    }

    private fun initViews(){
        tvNIP = findViewById(R.id.tvNip)
        tvNama = findViewById(R.id.tvNama)
        tvTgl = findViewById(R.id.tvTgl)
        tvJam = findViewById(R.id.tvJam)
        tvStatus = findViewById(R.id.tvStatus)
        tvAnak = findViewById(R.id.tvAnak)
        tvGol = findViewById(R.id.tvGol)
        tablePendapatan = findViewById(R.id.tablePendapatan)
        tvGaji = findViewById(R.id.tvGaji)
        btnKembali = findViewById(R.id.btnKembali)

        btnKembali.setOnClickListener {
            finish()
        }
    }

    private fun getData(){
        val bundle = intent.extras
        if(bundle != null){
            tvNIP.text = bundle.getString("pNIP")
            tvNama.text = bundle.getString("pNamaP")
            tvTgl.text = bundle.getString("pTgl")
            tvJam.text = bundle.getString("pJam")
            tvStatus.text = bundle.getString("pStatus")
            tvAnak.text = bundle.getInt("pAnak").toString()
            tvGol.text = bundle.getString("pGol")

            val pendapatanNama = bundle.getStringArrayList("pNama") ?: ArrayList()
            val pendapatanGaji = bundle.getIntegerArrayList("pGaji") ?: ArrayList()
            val pendapatanJumlah = bundle.getIntegerArrayList("pJumlah") ?: ArrayList()

            val gajiPokok = when(tvGol.text.toString()) {
                "Golongan I" -> 1000000
                "Golongan II" -> 2000000
                "Golongan III" -> 3000000
                else -> 4000000
            }

            val potongan = when(tvGol.text.toString()) {
                "Golongan I" -> gajiPokok * 5 / 100
                "Golongan II" -> gajiPokok * 10 / 100
                "Golongan III" -> gajiPokok * 11 / 100
                else -> gajiPokok * 12 / 100
            }

            val jumlahAnak = bundle.getInt("pAnak")
            val Anak = when {
                jumlahAnak > 4 -> 55000
                jumlahAnak > 3 -> 50000
                jumlahAnak >= 2 -> 45000
                else -> 0
            }

            val Keluarga = if(tvStatus.text.toString() == "Menikah") {
                jumlahAnak * 45000
            } else {
                0
            }

            var totalLain = 0
            for(i in pendapatanNama.indices){
                val nama = pendapatanNama[i]
                val gaji = pendapatanGaji[i]
                val jumlah = pendapatanJumlah[i]

                val lain = gaji * jumlah
                totalLain += lain

                val row = TableRow(this)

                val tvJumlah = TextView(this)
                tvJumlah.text = jumlah.toString()

                val tvDetail = TextView(this)
                tvDetail.text = "$nama - Rp. $gaji"

                row.addView(tvJumlah)
                row.addView(tvDetail)
                tablePendapatan.addView(row)
            }
            val gaji = gajiPokok + Anak + Keluarga + totalLain
            val gajiTotal = gaji - potongan

            tvGaji.text = "Rp. $gajiTotal"
        }
    }
}