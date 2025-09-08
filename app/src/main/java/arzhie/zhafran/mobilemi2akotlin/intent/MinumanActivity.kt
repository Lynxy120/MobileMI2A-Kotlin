package arzhie.zhafran.mobilemi2akotlin.intent

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
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
    private lateinit var etMeja: EditText
    private lateinit var etNama: EditText
    private lateinit var tvTgl: TextView
    private lateinit var tvJam: TextView
    private lateinit var chkPokat: CheckBox
    private lateinit var etPokat: EditText
    private lateinit var chkMangga: CheckBox
    private lateinit var etMangga: EditText
    private lateinit var chkJeruk: CheckBox
    private lateinit var etJeruk: EditText
    private lateinit var chkTeh: CheckBox
    private lateinit var etTeh: EditText
    private lateinit var spPelanggan: Spinner
    private lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_minuman)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
        setupDatePicker()
        setupTimePicker()
        setupSpinner()
        setupButton()
    }
    private fun initViews() {
        etMeja = findViewById(R.id.etMeja)
        etNama = findViewById(R.id.etNama)
        tvTgl = findViewById(R.id.tvTgl)
        tvJam = findViewById(R.id.tvJam)
        chkPokat = findViewById(R.id.chkPokat)
        etPokat = findViewById(R.id.etPokat)
        chkMangga = findViewById(R.id.chkMangga)
        etMangga = findViewById(R.id.etMangga)
        chkJeruk = findViewById(R.id.chkJeruk)
        etJeruk = findViewById(R.id.etJeruk)
        chkTeh = findViewById(R.id.chkTeh)
        etTeh = findViewById(R.id.etTeh)
        spPelanggan = findViewById(R.id.spPelanggan)
        btnSimpan = findViewById(R.id.btnSimpan)

        setCheckboxListener()
    }
    private fun setCheckboxListener() {
        chkPokat.setOnCheckedChangeListener { _, isChecked ->
            etPokat.isEnabled = isChecked
            if (!isChecked) etPokat.setText("")
        }
        chkMangga.setOnCheckedChangeListener { _, isChecked ->
            etMangga.isEnabled = isChecked
            if (!isChecked) etMangga.setText("")
        }
        chkJeruk.setOnCheckedChangeListener { _, isChecked ->
            etJeruk.isEnabled = isChecked
            if (!isChecked) etJeruk.setText("")
        }
        chkTeh.setOnCheckedChangeListener { _, isChecked ->
            etTeh.isEnabled = isChecked
            if (!isChecked) etTeh.setText("")
        }
    }

    private fun setupDatePicker() {
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
    }
    private fun setupTimePicker() {
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
    }
    private fun setupSpinner() {
        val listPelanggan =
            arrayOf("Baru", "Member")

        val adapterPelanggan : ArrayAdapter<*>
        adapterPelanggan = ArrayAdapter(this, R.layout.spin_style, listPelanggan)

        spPelanggan.adapter = adapterPelanggan
    }
    private fun setupButton(){
        btnSimpan.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("pMeja", etMeja.text.toString())
            bundle.putString("pNamaP", etNama.text.toString())
            bundle.putString("pTgl", tvTgl.text.toString())
            bundle.putString("pJam", tvJam.text.toString())
            bundle.putString("pPelanggan", spPelanggan.selectedItem.toString())

            val minumanNama = ArrayList<String>()
            val minumanHarga = ArrayList<Int>()
            val minumanJumlah = ArrayList<Int>()

            if (chkPokat.isChecked && etPokat.text.toString().isNotEmpty()) {
                minumanNama.add("Jus Pokat")
                minumanHarga.add(10000)
                minumanJumlah.add(etPokat.text.toString().toInt())
            }
            if (chkMangga.isChecked && etMangga.text.toString().isNotEmpty()) {
                minumanNama.add("Jus Mangga")
                minumanHarga.add(15000)
                minumanJumlah.add(etMangga.text.toString().toInt())
            }
            if (chkJeruk.isChecked && etJeruk.text.toString().isNotEmpty()) {
                minumanNama.add("Jus Jeruk")
                minumanHarga.add(8000)
                minumanJumlah.add(etJeruk.text.toString().toInt())
            }
            if (chkTeh.isChecked && etTeh.text.toString().isNotEmpty()) {
                minumanNama.add("Teh Panas")
                minumanHarga.add(5000)
                minumanJumlah.add(etTeh.text.toString().toInt())
            }

            bundle.putStringArrayList("pNama", minumanNama)
            bundle.putIntegerArrayList("pHarga", ArrayList(minumanHarga))
            bundle.putIntegerArrayList("pJumlah", ArrayList(minumanJumlah))

            val intent = Intent(this, HasilMinumanActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}