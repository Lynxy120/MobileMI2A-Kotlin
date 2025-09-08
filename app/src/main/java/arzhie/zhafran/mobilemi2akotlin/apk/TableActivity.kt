package arzhie.zhafran.mobilemi2akotlin.apk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.OnBackPressedCallback
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arzhie.zhafran.mobilemi2akotlin.R

class TableActivity : AppCompatActivity() {
    private lateinit var backCallback: OnBackPressedCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        // Setup back press handler dengan animasi
        backCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle back press dengan animasi
                finish()
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            }
        }
        onBackPressedDispatcher.addCallback(this, backCallback)

        // Terima data dari FormActivity
        val nama = intent.getStringExtra("nama") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val umur = intent.getStringExtra("umur") ?: ""
        val alamat = intent.getStringExtra("alamat") ?: ""

        // Tampilkan data dalam tabel
        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)

        addRowToTable(tableLayout, "Nama", nama)
        addRowToTable(tableLayout, "Email", email)
        addRowToTable(tableLayout, "Umur", umur)
        addRowToTable(tableLayout, "Alamat", alamat)

        val btnKembali = findViewById<Button>(R.id.btnKembali)
        btnKembali.setOnClickListener {
            // Kembali ke FormActivity
            backCallback.handleOnBackPressed()
        }

        val btnSelesai = findViewById<Button>(R.id.btnSelesai)
        btnSelesai.setOnClickListener {
            // Kembali ke LoginActivity dan clear stack
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }

    private fun addRowToTable(table: TableLayout, label: String, value: String) {
        val row = TableRow(this)
        val layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
        row.layoutParams = layoutParams

        val tvLabel = TextView(this).apply {
            text = label
            setPadding(16, 16, 16, 16)
            setBackgroundResource(R.drawable.table_cell_border)
        }

        val tvValue = TextView(this).apply {
            text = value
            setPadding(16, 16, 16, 16)
            setBackgroundResource(R.drawable.table_cell_border)
        }

        row.addView(tvLabel)
        row.addView(tvValue)
        table.addView(row)
    }
}