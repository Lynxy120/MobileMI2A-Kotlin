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

class EditNoteActivity : AppCompatActivity() {
    private lateinit var etTitle: EditText
    private lateinit var etKontent: EditText
    private lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_note)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etTitle = findViewById(R.id.etTitle)
        etKontent = findViewById(R.id.etKontent)
        btnSimpan = findViewById(R.id.btnSimpan)
    }
    override fun onStart() {
        super.onStart()
        editNote()
    }

    private fun editNote() {
        val bundle = intent.extras
        if (bundle != null){
            etTitle.setText(bundle.getString("title"))
            etKontent.setText(bundle.getString("kontent"))

            btnSimpan.setOnClickListener {
                val noteDao = NoteDao(this)
                val noteModel = NoteModel(
                    title = etTitle.text.toString(),
                    kontent = etKontent.text.toString()
                )
                val update = noteDao.updateNote(bundle.getInt("id",
                    0), noteModel)
                if (update){
                    startActivity(
                        Intent(
                            this@EditNoteActivity,
                            NoteActivity::class.java
                        )
                    )
                }
                else {
                    Toast.makeText(this,
                        "Data gagal di update",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}