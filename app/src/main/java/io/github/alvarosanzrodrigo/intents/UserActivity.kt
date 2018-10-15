package io.github.alvarosanzrodrigo.intents

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.DatePicker
import android.widget.TextView


class UserActivity : AppCompatActivity() {


    lateinit var etxtDate:TextView
    var TAG:String = "datePicker"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val intent = getIntent()
        val us = intent.extras.get(User.intentKey)
        us as User
        setResult(0)
    }

    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment.newInstance(object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, day: Int) {
                // +1 because january is zero
                val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
                etxtDate.setText(selectedDate)
            }
        })
        newFragment.show(supportFragmentManager, TAG)
    }

}
