package arzhie.zhafran.mobilemi2akotlin.sqlite.config

import android.content.ContentValues
import android.content.Context
import arzhie.zhafran.mobilemi2akotlin.sqlite.model.Jurusan_Model
import kotlin.text.insert

class JurusanDao(context: Context) {
    private val dbHelperl = DatabaseHelper(context)
    fun insertJurusan(jurusan: Jurusan_Model): Boolean {
        val db = dbHelperl.writableDatabase
        val values = ContentValues()
        values.put("nama_prodi", jurusan.nama_prodi)

        val result = db.insert("jurusan", null, values)
        return result != -1L
    }
}

