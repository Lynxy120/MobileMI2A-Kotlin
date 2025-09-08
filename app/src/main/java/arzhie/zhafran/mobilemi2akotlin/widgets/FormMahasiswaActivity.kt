package arzhie.zhafran.mobilemi2akotlin.widgets

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import arzhie.zhafran.mobilemi2akotlin.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FormMahasiswaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_mahasiswa)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Tambahkan Variabel
        val etNim = findViewById<EditText>(R.id.etNim)
        val etNama = findViewById<EditText>(R.id.etNama)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etTlpn = findViewById<EditText>(R.id.etTlpn)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)
        val spJurusan = findViewById<Spinner>(R.id.spJurusan)
        val spProgramStudi = findViewById<Spinner>(R.id.spProdi)
        val rgKelamin = findViewById<RadioGroup>(R.id.rgKelamin)
        val chkBaca = findViewById<CheckBox>(R.id.chkBaca)
        val chkTravel = findViewById<CheckBox>(R.id.chkTravel)
        val chkCoding = findViewById<CheckBox>(R.id.chkCoding)
        val tvTglLahir = findViewById<TextView>(R.id.tvTglLahir)
        val etJam = findViewById<TextView>(R.id.etJam)

        val listJurusan =
            arrayOf<String>("Teknologi Informasi","Akuntansi","Teknik Elektro","Administrasi Niaga","Teknik Sipil")
        val listProdi =
            arrayOf<String>("Manajemen Informatika","Akuntansi","Teknik Listrik","Bisnis Digital","Teknik Sipil")

        val adapterJurusan : ArrayAdapter<*>
        adapterJurusan = ArrayAdapter(this, R.layout.spin_style , listJurusan)
        val adapterProdi : ArrayAdapter<*>
        adapterProdi = ArrayAdapter(this, R.layout.spin_style, listProdi)

        //panggil widget spinner
        spJurusan.adapter = adapterJurusan
        spProgramStudi.adapter = adapterProdi

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

        //Event Ketika button di Klik
        btnSimpan.setOnClickListener {
            val pilKelamin = findViewById<RadioButton>(rgKelamin.checkedRadioButtonId)
            var pilHobby = ""

            if(chkBaca.isChecked){
                pilHobby+="${chkBaca.text}\n"
            }
            if(chkTravel.isChecked){
                pilHobby+="${chkTravel.text}\n"
            }
            if(chkCoding.isChecked){
                pilHobby+="${chkCoding.text}\n"
            }

            //Tampilkan ke tvHasil
            tvHasil.text = "Nim : ${etNim.text}\n" +
                    "Nama : ${etNama.text}\n" +
                    "Email : ${etEmail.text}\n" +
                    "No Tlpn : ${etTlpn.text}\n" +
                    "Jurusan : ${spJurusan.selectedItem}\n" +
                    "Program Studi : ${spProgramStudi.selectedItem}\n" +
                    "Jenis Kelamin : ${pilKelamin.text}\n"+
                    "Hobby : \n$pilHobby\n"+
                    "Tanggal Lahir : ${tvTglLahir.text}" +
                    "Jam Lahir : ${etJam.text}"
        }
    }
}