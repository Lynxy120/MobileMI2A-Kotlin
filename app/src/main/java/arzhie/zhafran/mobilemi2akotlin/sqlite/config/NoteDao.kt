package arzhie.zhafran.mobilemi2akotlin.sqlite.config

import android.content.ContentValues
import arzhie.zhafran.mobilemi2akotlin.sqlite.model.NoteModel
import android.content.Context
import kotlin.random.Random
import kotlin.text.insert

class NoteDao(context: Context){
    private val dbHelper = DatabaseHelper(context)

    fun insertNote(note: NoteModel): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put("title",note.title)
        values.put("kontent", note.content)
        val result = db.insert("note", null, values)
        return result != -1L




    }
    fun getAllNote(): List<NoteModel> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM note", null)
        return mutableListOf<NoteModel>().apply {
            if(cursor.moveToFirst()){
                do {
                    add(
                        NoteModel(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2)
                        )
                    )

                } while (cursor.moveToNext())
            }
            cursor.close()
        }
    }

    fun updateNote(id: Int, note: NoteModel): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put("title", note.title)
        values.put("kontent", note.content)
        val result = db.update("note", values, "id=?",
            arrayOf(id.toString()))
        return result > 0

    }

    fun deleteNote(id: Int): Boolean {
        val db = dbHelper.writableDatabase
        val result = db.delete("note", "id=?",
            arrayOf(id.toString()))
        return result > 0
    }
}
