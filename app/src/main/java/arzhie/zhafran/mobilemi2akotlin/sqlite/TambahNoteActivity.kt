package arzhie.zhafran.mobilemi2akotlin.sqlite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arzhie.zhafran.mobilemi2akotlin.R
import arzhie.zhafran.mobilemi2akotlin.sqlite.config.NoteDao
import arzhie.zhafran.mobilemi2akotlin.sqlite.model.NoteModel

class TambahNoteActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etContent: EditText
    private lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_note)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etTitle = findViewById(R.id.etTitle)
        etContent = findViewById(R.id.etContent)
        btnSimpan = findViewById(R.id.btnSimpan)

        btnSimpan.setOnClickListener { simpanNote() }
    }

    private fun simpanNote() {

        if (etTitle.text.isEmpty() || etContent.text.isEmpty()) {
            Toast.makeText(this, "Form tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            return
        }

        val noteDao = NoteDao(this)
        val note = NoteModel(
            title = etTitle.text.toString(),
            content = etContent.text.toString()
        )

        val insert = noteDao.insertNote(note)

        if (insert) {
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Data gagal disimpan", Toast.LENGTH_SHORT).show()
        }
    }
}
