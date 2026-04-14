package arzhie.zhafran.mobilemi2akotlin.menu

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arzhie.zhafran.mobilemi2akotlin.R
import arzhie.zhafran.mobilemi2akotlin.intent.GajiActivity
import arzhie.zhafran.mobilemi2akotlin.intent.MinumanActivity
import arzhie.zhafran.mobilemi2akotlin.map.MapsActivity
import arzhie.zhafran.mobilemi2akotlin.sqlite.NoteActivity
import arzhie.zhafran.mobilemi2akotlin.sqlite.ProdiTambahActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuActivity : AppCompatActivity() {
    private lateinit var toolBar: Toolbar
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        toolBar = findViewById(R.id.toolBar)
        bottomNav = findViewById(R.id.bottomNav)

        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId){
                   R.id.bottom_home -> {
                    startActivity(Intent(this@MenuActivity, NoteActivity::class.java))
                    true
                }
                R.id.bottom_order -> {
                    startActivity(Intent(this@MenuActivity, MinumanActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.top_logout -> {
                startActivity(Intent(this@MenuActivity, MapsActivity::class.java))
                true
            }
            R.id.top_group -> {
                startActivity(Intent(this@MenuActivity, ProdiTambahActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}