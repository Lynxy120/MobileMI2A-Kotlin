package arzhie.zhafran.mobilemi2akotlin.intent

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arzhie.zhafran.mobilemi2akotlin.R

class HasilActivity : AppCompatActivity() {
    private lateinit var tvNidn: TextView
    private lateinit var tvNama: TextView
    private lateinit var tvUmur: TextView
    private  lateinit var  btnKembali: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hasil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvNidn = findViewById(R.id.tvNidn)
        tvNama = findViewById(R.id.tvNama)
        tvUmur = findViewById(R.id.tvUmur)
        btnKembali = findViewById(R.id.btnKembali)
    }

    override fun onStart() {
        super.onStart()
        getData()
    }
    fun getData(){
        val bundle = intent.extras
        if (bundle!=null){
            tvNidn.text = "NIDN : ${bundle.getString("pNidn")}"
            tvNama.text = "Nama : ${bundle.getString("pNama")}"
            tvUmur.text = "Umur : ${bundle.getString("pUmur")}"
        }
        btnKembali.setOnClickListener {
            finish();
        }
    }
}