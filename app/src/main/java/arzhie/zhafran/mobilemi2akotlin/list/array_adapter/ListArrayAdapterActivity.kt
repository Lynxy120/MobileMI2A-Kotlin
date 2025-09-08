package arzhie.zhafran.mobilemi2akotlin.list.array_adapter

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arzhie.zhafran.mobilemi2akotlin.R

class ListArrayAdapterActivity : AppCompatActivity() {
    private lateinit var lvPemrograman: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_array_adapter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lvPemrograman = findViewById(R.id.lvPemrograman)
    }

    override fun onStart() {
        super.onStart()
        val listPemrograman = arrayOf("Kotlin", "Java", "C#", "Phyton", "PHP", "Dart", "C++", "C")
        val arrayAdapterPemrograman = ArrayAdapter(this, R.layout.list_style, listPemrograman)
        lvPemrograman.adapter = arrayAdapterPemrograman
    }
}