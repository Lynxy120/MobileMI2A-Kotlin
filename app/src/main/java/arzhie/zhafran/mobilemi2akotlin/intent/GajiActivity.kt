package arzhie.zhafran.mobilemi2akotlin.intent

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
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

class GajiActivity : AppCompatActivity() {
    private lateinit var etNIP: EditText
    private lateinit var etNama: EditText
    private lateinit var tvTgl: TextView
    private lateinit var tvJam: TextView
    private lateinit var rdStatus: RadioGroup
    private lateinit var etAnak: EditText
    private lateinit var spGol: Spinner
    private lateinit var chkMakan: CheckBox
    private lateinit var chkLembur: CheckBox
    private lateinit var chkDinas: CheckBox
    private lateinit var etMakan: EditText
    private lateinit var etLembur: EditText
    private lateinit var etDinas: EditText
    private lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gaji)
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
    private fun initViews(){
        etNIP = findViewById(R.id.etNIP)
        etNama = findViewById(R.id.etNama)
        tvTgl = findViewById(R.id.tvTgl)
        tvJam = findViewById(R.id.tvJam)
        rdStatus = findViewById(R.id.rdStatus)
        etAnak = findViewById(R.id.etAnak)
        spGol = findViewById(R.id.spGol)
        chkMakan = findViewById(R.id.chkMakan)
        etMakan = findViewById(R.id.etMakan)
        chkLembur = findViewById(R.id.chkLembur)
        etLembur = findViewById(R.id.etLembur)
        chkDinas = findViewById(R.id.chkDinas)
        etDinas = findViewById(R.id.etDinas)
        btnSimpan = findViewById(R.id.btnSimpan)

        setCheckboxListener()
    }
    private fun setCheckboxListener() {
        chkMakan.setOnCheckedChangeListener { _, isChecked ->
            etMakan.isEnabled = isChecked
            if (!isChecked) etMakan.setText("")
        }
        chkLembur.setOnCheckedChangeListener { _, isChecked ->
            etLembur.isEnabled = isChecked
            if (!isChecked) etLembur.setText("")
        }
        chkDinas.setOnCheckedChangeListener { _, isChecked ->
            etDinas.isEnabled = isChecked
            if (!isChecked) etDinas.setText("")
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
        val listGol =
            arrayOf("Golongan I","Golongan II","Golongan III","Golongan IV")

        val adapterGol : ArrayAdapter<*>
        adapterGol = ArrayAdapter(this, R.layout.spin_style, listGol)

        spGol.adapter = adapterGol
    }
    private fun setupButton(){
        btnSimpan.setOnClickListener {
            val pilStatus = findViewById<RadioButton>(rdStatus.checkedRadioButtonId)
            val jumlahAnak = if (etAnak.text.toString().isEmpty()) {
                0
            } else {
                etAnak.text.toString().toInt()
            }
            val bundle = Bundle()
            bundle.putString("pNIP", etNIP.text.toString())
            bundle.putString("pNamaP", etNama.text.toString())
            bundle.putString("pTgl", tvTgl.text.toString())
            bundle.putString("pJam", tvJam.text.toString())
            bundle.putString("pStatus", pilStatus.text.toString())
            bundle.putInt("pAnak", jumlahAnak)
            bundle.putString("pGol", spGol.selectedItem.toString())

            val pendapatanNama = ArrayList<String>()
            val pendapatanGaji = ArrayList<Int>()
            val pendapatanJumlah = ArrayList<Int>()

            if (chkMakan.isChecked && etMakan.text.toString().isNotEmpty()) {
                pendapatanNama.add("Uang Makan")
                pendapatanGaji.add(30000)
                pendapatanJumlah.add(etMakan.text.toString().toInt())
            }
            if (chkLembur.isChecked && etLembur.text.toString().isNotEmpty()) {
                pendapatanNama.add("Lembur")
                pendapatanGaji.add(25000)
                pendapatanJumlah.add(etLembur.text.toString().toInt())
            }
            if (chkDinas.isChecked && etDinas.text.toString().isNotEmpty()) {
                pendapatanNama.add("Dinas")
                pendapatanGaji.add(35000)
                pendapatanJumlah.add(etDinas.text.toString().toInt())
            }

            bundle.putStringArrayList("pNama", pendapatanNama)
            bundle.putIntegerArrayList("pGaji", ArrayList(pendapatanGaji))
            bundle.putIntegerArrayList("pJumlah", ArrayList(pendapatanJumlah))

            val intent = Intent(this, HasilGajiActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}