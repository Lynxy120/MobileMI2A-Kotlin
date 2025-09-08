package arzhie.zhafran.mobilemi2akotlin.sqlite

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arzhie.zhafran.mobilemi2akotlin.R
import arzhie.zhafran.mobilemi2akotlin.sqlite.config.JurusanDao
import arzhie.zhafran.mobilemi2akotlin.sqlite.model.Jurusan_Model

class ProdiTambahActivity : AppCompatActivity() {
    private lateinit var etNama: EditText
    private lateinit var btnSimpan: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_prodi_tambah)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etNama = findViewById(R.id.etNama)
        btnSimpan = findViewById(R.id.btnSimpan)
    }
    override fun onStart() {
        super.onStart()
        simpanProdi()
    }
    private fun simpanProdi(){
        btnSimpan.setOnClickListener {
            val jurusanDao = JurusanDao(this)
            val jurusanModel = Jurusan_Model(
                nama_prodi = etNama.text.toString(),

                )
            val insert = jurusanDao.insertJurusan(jurusanModel)
            if (insert) {
                Toast.makeText(
                    this,
                    "Data berhasil disimpan",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                Toast.makeText(
                    this,
                    "Data gagal disimpan",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}