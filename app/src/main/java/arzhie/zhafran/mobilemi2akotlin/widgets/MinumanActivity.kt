package arzhie.zhafran.mobilemi2akotlin.widgets

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arzhie.zhafran.mobilemi2akotlin.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MinumanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_minuman2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etMeja = findViewById<EditText>(R.id.etMeja)
        val etNama = findViewById<EditText>(R.id.etNama)
        val tvTgl = findViewById<TextView>(R.id.tvTgl)
        val tvJam = findViewById<TextView>(R.id.tvJam)
        val chkPokat = findViewById<CheckBox>(R.id.chkPokat)
        val etPokat = findViewById<EditText>(R.id.etPokat)
        val chkMangga = findViewById<CheckBox>(R.id.chkMangga)
        val etMangga = findViewById<EditText>(R.id.etMangga)
        val chkJeruk = findViewById<CheckBox>(R.id.chkJeruk)
        val etJeruk = findViewById<EditText>(R.id.etJeruk)
        val chkTeh = findViewById<CheckBox>(R.id.chkTeh)
        val etTeh = findViewById<EditText>(R.id.etTeh)
        val spPelanggan = findViewById<Spinner>(R.id.spPelanggan)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val llHasil = findViewById<LinearLayout>(R.id.llHasil)

        val listPelanggan =
            arrayOf("Baru","Member")

        val adapterPelanggan: ArrayAdapter<*>
        adapterPelanggan = ArrayAdapter(this, R.layout.spin_style, listPelanggan)

        spPelanggan.adapter = adapterPelanggan

        val myCalendar = Calendar.getInstance()
        var datePicker: DatePickerDialog.OnDateSetListener
        datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayofMonth ->
            myCalendar[Calendar.YEAR] = year
            myCalendar[Calendar.MONTH] = month
            myCalendar[Calendar.DAY_OF_MONTH] = dayofMonth
            val formatIndo = "dd-MM-yyyy"
            val stringFormat = SimpleDateFormat(formatIndo, Locale.US)
            tvTgl.text = stringFormat.format(myCalendar.time)
        }

        tvTgl.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }

        val currentTime = Calendar.getInstance()
        val timePicker: TimePickerDialog.OnTimeSetListener
        timePicker = TimePickerDialog.OnTimeSetListener { _, hourofDay, minute ->
            currentTime.set(Calendar.HOUR_OF_DAY, hourofDay)
            currentTime.set(Calendar.MINUTE, minute)
            val formatTime = SimpleDateFormat("HH:mm").format(currentTime.time)
            tvJam.text = formatTime
        }

        tvJam.setOnClickListener {
            TimePickerDialog(
                this,
                timePicker,
                currentTime.get(Calendar.HOUR_OF_DAY),
                currentTime.get(Calendar.MINUTE),
                true
            ).show()
        }

        btnSimpan.setOnClickListener {
            llHasil.removeAllViews()

            val meja = etMeja.text.toString()
            val nama = etNama.text.toString()
            val tgl = tvTgl.text.toString()
            val jam = tvJam.text.toString()
            val jenis = spPelanggan.selectedItem.toString()

            val tvData = TextView(this)
            tvData.text =
                        "No Meja: $meja\n" +
                        "Nama: $nama\n" +
                        "Tanggal: $tgl\n" +
                        "Jam: $jam\n" +
                        "Jenis: $jenis"
            llHasil.addView(tvData)

            var total = 0
            val tvMinuman = TextView(this)
            var textMinuman = "Minuman:\n"

            if (chkPokat.isChecked && etPokat.text.isNotEmpty()) {
                val jumlah = etPokat.text.toString().toInt()
                val harga = 10000
                total += jumlah * harga
                textMinuman += "Jus Pokat: $jumlah x $harga\n"
            }

            if (chkMangga.isChecked && etMangga.text.isNotEmpty()) {
                val jumlah = etMangga.text.toString().toInt()
                val harga = 15000
                total += jumlah * harga
                textMinuman += "Jus Mangga: $jumlah x $harga\n"
            }

            if (chkJeruk.isChecked && etJeruk.text.isNotEmpty()) {
                val jumlah = etJeruk.text.toString().toInt()
                val harga = 8000
                total += jumlah * harga
                textMinuman += "Jus Jeruk: $jumlah x $harga\n"
            }

            if (chkTeh.isChecked && etTeh.text.isNotEmpty()) {
                val jumlah = etTeh.text.toString().toInt()
                val harga = 5000
                total += jumlah * harga
                textMinuman += "Teh Panas: $jumlah x $harga\n"
            }

            tvMinuman.text = textMinuman
            llHasil.addView(tvMinuman)

            val tvTotal = TextView(this)
            tvTotal.text = "Total: Rp$total"
            llHasil.addView(tvTotal)

        }
    }
}
