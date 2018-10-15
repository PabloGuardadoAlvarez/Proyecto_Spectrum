package io.github.alvarosanzrodrigo.intents

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*


class UserActivity : AppCompatActivity() {

    lateinit var txtfecha: EditText
    lateinit var txtnombre: TextView
    lateinit var txtapellido: TextView
    lateinit var txtapellido2: TextView
    lateinit var txtciudad: TextView
    lateinit var txtdireccion: TextView
    lateinit var txtCD: TextView
    lateinit var txttelefono: TextView
    lateinit var tipotelefono: Spinner
    lateinit var cabeceraform: TextView

    var TAG: String = "datePicker"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        // val intent = getIntent()
        // val us = intent.extras.get(User.intentKey)
        // us as User
        // setResult(0)
        txtfecha = findViewById(R.id.etxtDate)
        txtapellido = findViewById(R.id.etxtSurname)
        txtapellido2 = findViewById(R.id.etxtSurname2)
        txtciudad = findViewById(R.id.etxtciudad)
        txtCD = findViewById(R.id.etxtCD)
        txtnombre = findViewById(R.id.etxtName)
        txtdireccion = findViewById(R.id.etxtciudad)
        txttelefono = findViewById(R.id.etxttelefono)
        tipotelefono = findViewById(R.id.spinnerTelefono)
        cabeceraform = findViewById(R.id.cabecera)

    }

    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment.newInstance(object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, day: Int) {
                // +1 because january is zero
                val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
                txtfecha.setText(selectedDate)
            }
        })
        newFragment.show(supportFragmentManager, TAG)
    }

    fun btnpressd(view: View) {

        if (txtnombre.text.isEmpty()||txtapellido.text.isEmpty()||txtapellido2.text.isEmpty()||txtdireccion.text.isEmpty()||txtCD.text.isEmpty()||txtciudad.text.isEmpty()||txtfecha.text.isEmpty()||txttelefono.text.isEmpty()) {

            Toast.makeText(this, "Asegurese de rellenar todos los campos", Toast.LENGTH_LONG).show()
        } else {

            val intent = Intent(this, PersonalData::class.java)

            val us = User()
            us.name = txtfecha.text.toString()
            us.surname = txtapellido.text.toString()
            us.surname2 = txtapellido2.text.toString()
            us.adress = txtdireccion.text.toString()
            us.city = txtciudad.text.toString()
            us.postalCode = txtCD.text.toString()
            us.phone = Integer.parseInt(txttelefono.text.toString())
            us.phoneType = tipotelefono.toString()
            intent.putExtra(User.intentKey, us)
            startActivityForResult(intent, 0)


        }

    }


}
