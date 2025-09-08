package arzhie.zhafran.mobilemi2akotlin.apk

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.OnBackPressedCallback
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arzhie.zhafran.mobilemi2akotlin.R
import com.google.android.material.textfield.TextInputEditText

class FormActivity : AppCompatActivity() {
    // Deklarasikan backCallback di tingkat kelas
    private lateinit var backCallback: OnBackPressedCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        // Enable back button di action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Inisialisasi backCallback
        backCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle back press dengan animasi
                finish()
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            }
        }
        // Tambahkan callback ke dispatcher
        onBackPressedDispatcher.addCallback(this, backCallback)

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            if (validateForm()) {
                // Ambil data dari form
                val etNama = findViewById<TextInputEditText>(R.id.etNama)
                val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
                val etUmur = findViewById<TextInputEditText>(R.id.etUmur)
                val etAlamat = findViewById<TextInputEditText>(R.id.etAlamat)

                val nama = etNama.text.toString()
                val email = etEmail.text.toString()
                val umur = etUmur.text.toString()
                val alamat = etAlamat.text.toString()

                // Pindah ke TableActivity dan kirim data
                val intent = Intent(this, TableActivity::class.java)
                intent.putExtra("nama", nama)
                intent.putExtra("email", email)
                intent.putExtra("umur", umur)
                intent.putExtra("alamat", alamat)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        val etNama = findViewById<TextInputEditText>(R.id.etNama)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etUmur = findViewById<TextInputEditText>(R.id.etUmur)
        val etAlamat = findViewById<TextInputEditText>(R.id.etAlamat)

        val nama = etNama.text.toString()
        val email = etEmail.text.toString()
        val umur = etUmur.text.toString()
        val alamat = etAlamat.text.toString()

        // Validasi nama
        if (nama.isEmpty()) {
            etNama.error = "Nama harus diisi"
            isValid = false
        } else {
            etNama.error = null
        }

        // Validasi email
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Email tidak valid"
            isValid = false
        } else {
            etEmail.error = null
        }

        // Validasi umur
        if (umur.isEmpty() || umur.toInt() < 0 || umur.toInt() > 150) {
            etUmur.error = "Umur tidak valid"
            isValid = false
        } else {
            etUmur.error = null
        }

        // Validasi alamat
        if (alamat.isEmpty()) {
            etAlamat.error = "Alamat harus diisi"
            isValid = false
        } else {
            etAlamat.error = null
        }

        return isValid
    }

    override fun onSupportNavigateUp(): Boolean {
        // Panggil handleOnBackPressed dari backCallback
        backCallback.handleOnBackPressed()
        return true
    }
}