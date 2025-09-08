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
import com.google.android.material.bottomnavigation.BottomNavigationView

class ShopeeActivity : AppCompatActivity() {
    private lateinit var toolBar: Toolbar
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shopee)
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
                    Toast.makeText(applicationContext, "Home", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.bottom_trending -> {
                    Toast.makeText(applicationContext, "Trending", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.bottom_Live -> {
                    Toast.makeText(applicationContext, "Live & Video", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.bottom_Notif -> {
                    Toast.makeText(applicationContext, "Notifikasi", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.bottom_Saya -> {
                    Toast.makeText(applicationContext, "Saya", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.atas_shopee, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.top_Shop -> {
                startActivity(Intent(this@ShopeeActivity, MinumanActivity::class.java))
                true
            }
            R.id.top_Chat -> {
                Toast.makeText(applicationContext, "Chat", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}