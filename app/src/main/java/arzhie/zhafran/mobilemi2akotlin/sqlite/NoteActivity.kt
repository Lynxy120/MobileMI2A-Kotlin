package arzhie.zhafran.mobilemi2akotlin.sqlite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import arzhie.zhafran.mobilemi2akotlin.R
import arzhie.zhafran.mobilemi2akotlin.sqlite.adapter.NoteAdapter
import arzhie.zhafran.mobilemi2akotlin.sqlite.config.NoteDao
import arzhie.zhafran.mobilemi2akotlin.sqlite.model.NoteModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteActivity : AppCompatActivity() {
    private lateinit var rvNote: RecyclerView
    private lateinit var fabTambahNote: FloatingActionButton
    private lateinit var notes: MutableList<NoteModel>
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_note)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvNote = findViewById(R.id.rvNote)
        rvNote.layoutManager = LinearLayoutManager(this)
        fabTambahNote = findViewById(R.id.fabTambahNote)
    }

    override fun onStart() {
        super.onStart()
        fabTambahNote.setOnClickListener {
            startActivity(android.content.Intent(this@NoteActivity,
                TambahNoteActivity::class.java))
        }
        getAllData()
    }

    private fun getAllData() {
        val noteDao = NoteDao(this)
        notes = noteDao.getAllNote().toMutableList()
        noteAdapter = NoteAdapter(notes) { note ->
            deleteNote(note)
        }
        rvNote.adapter = noteAdapter
        Log.d("NoteActivity", notes.toString())
    }

    private fun deleteNote(note: NoteModel) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Hapus Catatan")
        builder.setMessage("Apakah Anda yakin ingin menghapus catatan ini?")
        builder.setPositiveButton("Ya") { dialog, _ ->
            val noteDao = NoteDao(this)
            val isDeleted = noteDao.deleteNote(note.id)

            if (isDeleted) {
                Toast.makeText(this, "Catatan berhasil dihapus", Toast.LENGTH_SHORT).show()
                getAllData()
            } else {
                Toast.makeText(this, "Gagal menghapus catatan", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
        builder.setNegativeButton("Batal") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}