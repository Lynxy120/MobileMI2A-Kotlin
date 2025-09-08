package arzhie.zhafran.mobilemi2akotlin.widgets

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arzhie.zhafran.mobilemi2akotlin.R
import arzhie.zhafran.mobilemi2akotlin.intent.HasilActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FormPegawaiActivity : AppCompatActivity() {
    private lateinit var etNip: TextView
    private lateinit var etNama: TextView
    private lateinit var etTlhr: TextView
    private lateinit var etUmur: TextView
    private lateinit var etNik: TextView
    private lateinit var etKk: TextView
    private lateinit var btnSimpan: Button
    private lateinit var tvHasil: TextView
    private lateinit var spPend: Spinner
    private lateinit var rgAgama: RadioGroup
    private lateinit var chkRenang: CheckBox
    private lateinit var chkLari: CheckBox
    private lateinit var chkBola: CheckBox
    private lateinit var chkPadel: CheckBox
    private lateinit var tvTglLahir: TextView
    private lateinit var etJam: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_pegawai)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etNip = findViewById(R.id.etNip)
        etNama = findViewById(R.id.etNama)
        etTlhr = findViewById(R.id.etTlhr)
        etUmur = findViewById(R.id.etUmur)
        etNik = findViewById(R.id.etNik)
        etKk = findViewById(R.id.etKk)
        btnSimpan = findViewById(R.id.btnSimpan)
        tvHasil = findViewById(R.id.tvHasil)
        spPend = findViewById(R.id.spPend)
        rgAgama = findViewById(R.id.rdAgama)
        chkRenang = findViewById(R.id.chkRenang)
        chkLari = findViewById(R.id.chkLari)
        chkBola = findViewById(R.id.chkBola)
        chkPadel = findViewById(R.id.chkPadel)
        tvTglLahir = findViewById(R.id.tvTglLahir)
        etJam = findViewById(R.id.etJam)

        val etNip = findViewById<TextView>(R.id.etNip)
        val etNama = findViewById<TextView>(R.id.etNama)
        val etTlhr = findViewById<TextView>(R.id.etTlhr)
        val etUmur = findViewById<TextView>(R.id.etUmur)
        val etNik = findViewById<TextView>(R.id.etNik)
        val etKk = findViewById<TextView>(R.id.etKk)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)
        val spPend = findViewById<Spinner>(R.id.spPend)
        val rgAgama = findViewById<RadioGroup>(R.id.rdAgama)
        val chkRenang = findViewById<CheckBox>(R.id.chkRenang)
        val chkLari = findViewById<CheckBox>(R.id.chkLari)
        val chkBola = findViewById<CheckBox>(R.id.chkBola)
        val chkPadel = findViewById<CheckBox>(R.id.chkPadel)
        val tvTglLahir = findViewById<TextView>(R.id.tvTglLahir)
        val etJam = findViewById<TextView>(R.id.etJam)

        val listPend =
            arrayOf<String>("SMA","D3","D4/S1","S2")

        val adapterPend : ArrayAdapter<*>
        adapterPend = ArrayAdapter(this, R.layout.spin_style, listPend)

        spPend.adapter = adapterPend

        val myCalendar = Calendar.getInstance()
        var datePicker : DatePickerDialog.OnDateSetListener
        datePicker = DatePickerDialog.OnDateSetListener{
                _,year,month,dayofMonth ->
            myCalendar[Calendar.YEAR] = year
            myCalendar[Calendar.MONTH] = month
            myCalendar[Calendar.DAY_OF_MONTH] = dayofMonth
            val formatIndo = "dd-MM-yyyy"
            val stringFormat = SimpleDateFormat(formatIndo, Locale.US)

            //isi tanggal ke textview
            tvTglLahir.text = stringFormat.format(myCalendar.time)
        }
        //tampilkan kalender
        tvTglLahir.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }

        val currentTime = Calendar.getInstance()
        val timePicker : TimePickerDialog.OnTimeSetListener
        timePicker = TimePickerDialog.OnTimeSetListener{
                _,hourofDay, minute ->
            currentTime.set(Calendar.HOUR_OF_DAY, hourofDay)
            currentTime.set(Calendar.MINUTE, minute)
            val formatTime = SimpleDateFormat("HH:mm").format(currentTime.time)
            etJam.text = formatTime
        }

        //tampilkan jam
        etJam.setOnClickListener {
            TimePickerDialog(
                this,
                timePicker,
                currentTime.get(Calendar.HOUR_OF_DAY),
                currentTime.get(Calendar.MINUTE),
                true
            ).show()
        }

        btnSimpan.setOnClickListener {
            val pilAgama = findViewById<RadioButton>(rgAgama.checkedRadioButtonId)
            var pilOlahRaga = ""

            if(chkRenang.isChecked){
                pilOlahRaga+="${chkRenang.text}\n"
            }
            if(chkLari.isChecked){
                pilOlahRaga+="${chkLari.text}\n"
            }
            if(chkBola.isChecked){
                pilOlahRaga+="${chkBola.text}\n"
            }
            if(chkPadel.isChecked){
                pilOlahRaga+="${chkPadel.text}\n"
            }

            val bundle = Bundle()
            bundle.putString("pNip", etNip.text.toString())
            bundle.putString("pNama", etNama.text.toString())
            bundle.putString("pTlhr", etTlhr.text.toString())
            bundle.putString("pUmur", etUmur.text.toString())
            bundle.putString("pNik", etNik.text.toString())
            bundle.putString("pKk", etKk.text.toString())
            bundle.putString("pPend", spPend.selectedItem.toString())
            bundle.putString("pAgama", pilAgama.text.toString())
            bundle.putString("pOlahRaga", pilOlahRaga)
            bundle.putString("pTglLahir", tvTglLahir.text.toString())
            bundle.putString("pJam", etJam.text.toString())

            val intent = Intent(this, HasilPegawaiActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)


            tvHasil.text =
                    "NIP            :${etNip.text}\n" +
                    "NAMA           :${etNama.text}\n" +
                    "Tempat Lahir   :${etTlhr.text}\n" +
                    "UMUR           :${etUmur.text}\n" +
                    "NIK            :${etNik.text}\n" +
                    "NO KK          :${etKk.text}\n" +
                    "Pendidikan : ${spPend.selectedItem}\n" +
                    "Agama : ${pilAgama.text}\n" +
                    "OlahRaga : \n$pilOlahRaga" +
                    "Tanggal Masuk : ${tvTglLahir.text}\n"+
                    "Jam Masuk : ${etJam.text}"
            }
    }
}