package arzhie.zhafran.mobilemi2akotlin.sqlite.config

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context,
    "note.db",null, 2)
{
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE note(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "kontent TEXT)")
        db?.execSQL("CREATE TABLE jurusan" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nama_prodi TEXT)")

    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        db?.execSQL("DROP TABLE IF EXISTS note")
        db?.execSQL("DROP TABLE IF EXISTS jurusan")
        onCreate(db)

    }
}