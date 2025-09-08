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

class HasilMinumanActivity : AppCompatActivity() {
    private lateinit var tvMeja: TextView
    private lateinit var tvNama: TextView
    private lateinit var tvTgl: TextView
    private lateinit var tvJam: TextView
    private lateinit var tvPelanggan: TextView
    private lateinit var tableBeli: TableLayout
    private lateinit var btnKembali: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hasil_minuman)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
    }
    private fun initViews(){
        tvMeja = findViewById(R.id.tvMeja)
        tvNama = findViewById(R.id.tvNama)
        tvTgl = findViewById(R.id.tvTgl)
        tvJam = findViewById(R.id.tvJam)
        tvPelanggan = findViewById(R.id.tvPelanggan)
        tableBeli = findViewById(R.id.tableBeli)
        btnKembali = findViewById(R.id.btnKembali)
    }
    override fun onStart() {
        super.onStart()
        getData();
    }
    private fun getData(){
        val bundle = intent.extras
        if(bundle != null){
            tvMeja.text = bundle.getString("pMeja")
            tvNama.text = bundle.getString("pNamaP")
            tvTgl.text = bundle.getString("pTgl")
            tvJam.text = bundle.getString("pJam")
            tvPelanggan.text = bundle.getString("pPelanggan")

            val minumNama = bundle.getStringArrayList("pNama") ?: ArrayList()
            val minumHarga = bundle.getIntegerArrayList("pHarga") ?: ArrayList()
            val minumJumlah = bundle.getIntegerArrayList("pJumlah") ?: ArrayList()

            var totalHarga = 0
            var diskon = 0
            var hargabayar = 0
            var beli = 0

            for(i in minumNama.indices){
                val nama = minumNama[i]
                val harga = minumHarga[i]
                val jumlah = minumJumlah[i]

                val subtotal = harga * jumlah
                totalHarga += subtotal

                if(tvPelanggan.text.toString() == "Baru"){
                    diskon = totalHarga * 10/100
                } else
                    diskon = totalHarga * 20/100

                beli = totalHarga - diskon

                if (totalHarga >= 100000) {
                    hargabayar = beli - 10000
                } else
                    hargabayar = beli

                val row = TableRow(this)

                val tvJumlah = TextView(this)
                tvJumlah.text = jumlah.toString()

                val tvMinum = TextView(this)
                tvMinum.text = "$nama - Rp. $harga"

                row.addView(tvJumlah)
                row.addView(tvMinum)
                tableBeli.addView(row)
            }
            val totalrow = TableRow(this)

            val tvlabelTotal = TextView(this)
            tvlabelTotal.text ="Total Harga :"

            val tvTotal = TextView(this)
            tvTotal.text = "Rp$hargabayar"

            totalrow.addView(tvlabelTotal)
            totalrow.addView(tvTotal)
            tableBeli.addView(totalrow)
        }
        btnKembali.setOnClickListener {
            finish()
        }
    }
}