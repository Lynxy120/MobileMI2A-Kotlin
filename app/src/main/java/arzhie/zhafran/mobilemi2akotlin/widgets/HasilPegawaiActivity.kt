package arzhie.zhafran.mobilemi2akotlin.widgets

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arzhie.zhafran.mobilemi2akotlin.R

class HasilPegawaiActivity : AppCompatActivity() {
    private lateinit var tvNip: TextView
    private lateinit var tvNama: TextView
    private lateinit var tvTlhr: TextView
    private lateinit var tvUmur: TextView
    private lateinit var tvNik: TextView
    private lateinit var tvKk: TextView
    private lateinit var tvPend: TextView
    private lateinit var tvAgama: TextView
    private lateinit var tvOlahraga: TextView
    private lateinit var tvTgl: TextView
    private lateinit var tvJam: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hasil_pegawai2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvNip = findViewById(R.id.tvNip)
        tvNama = findViewById(R.id.tvNama)
        tvTlhr = findViewById(R.id.tvTlhr)
        tvUmur = findViewById(R.id.tvUmur)
        tvNik = findViewById(R.id.tvNik)
        tvKk = findViewById(R.id.tvKk)
        tvPend = findViewById(R.id.tvPend)
        tvAgama = findViewById(R.id.tvAgama)
        tvOlahraga = findViewById(R.id.tvOlahraga)
        tvTgl = findViewById(R.id.tvTgl)
        tvJam = findViewById(R.id.tvJam)
    }
    override fun onStart() {
        super.onStart()
        getData()
    }
    fun getData() {
        val bundle = intent.extras
        if (bundle != null) {
            tvNip.text = "NIP : ${bundle.getString("pNip")}"
            tvNama.text = "Nama : ${bundle.getString("pNama")}"
            tvTlhr.text = "Tempat Lahir : ${bundle.getString("pTlhr")}"
            tvUmur.text = "Umur : ${bundle.getString("pUmur")}"
            tvNik.text = "NIK : ${bundle.getString("pNik")}"
            tvKk.text = "No KK : ${bundle.getString("pKk")}"
            tvPend.text = "Pendidikan : ${bundle.getString("pPend")}"
            tvAgama.text = "Agama : ${bundle.getString("pAgama")}"
            tvOlahraga.text = "Olahraga : ${bundle.getString("pOlahRaga")}"
            tvTgl.text = "Tanggal Masuk : ${bundle.getString("pTglLahir")}"
            tvJam.text = "Jam Masuk : ${bundle.getString("pJam")}"
        }
    }
}