package arzhie.zhafran.mobilemi2akotlin.sqlite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
        fabTambahNote = findViewById(R.id.fabTambahNote)
    }
    override fun onStart() {
        super.onStart()
        fabTambahNote.setOnClickListener {
            startActivity(
                Intent(
                    this@NoteActivity,
                    TambahNoteActivity::class.java
                )
            )
        }
        getAllData()
    }
    private fun getAllData(){
        val noteDao = NoteDao(this)
        notes = noteDao.getAllNote().toMutableList()
        noteAdapter = NoteAdapter(notes,
            object : NoteAdapter.OnAdapterListener{
                override fun onClick(data: NoteModel) {
                    val bundle = Bundle()
                    bundle.putInt("id", data.id)
                    bundle.putString("title", data.title)
                    bundle.putString("kontent", data.kontent)
                    val intent = Intent(this@NoteActivity, EditNoteActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }

                override fun onLongClick(
                    data: NoteModel,
                    position: Int
                ) {
                    val delete = noteDao.deleteNote(data.id)
                    if(delete){
                        notes.removeAt(position)
                        noteAdapter.notifyItemRemoved(position)
                    }else{
                        Toast.makeText(
                            this@NoteActivity,
                            "Data gagal dihapus",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        rvNote.adapter = noteAdapter
        Log.d("NoteActivity",notes.toString())
    }
}