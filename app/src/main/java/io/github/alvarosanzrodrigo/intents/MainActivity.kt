package io.github.alvarosanzrodrigo.intents

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife

class MainActivity : AppCompatActivity() {
    //@BindView(R.id.editText2) lateinit var txtEmail : EditText
    lateinit var txtEmail: EditText
    lateinit var txtPsw: EditText
    lateinit var btnGo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtEmail = findViewById(R.id.editText2)
        txtPsw = findViewById(R.id.editText3)
        btnGo = findViewById(R.id.btnGo)
        txtWatch(txtEmail)
        txtWatch(txtPsw)
    }

    fun onGoPressed(view: View) {
        if (txtEmail.text.toString().equals("ejemplo@ejemplo.com") && txtPsw.text.toString().equals("123")) {
            val intent = Intent(this@MainActivity, UserActivity::class.java)
            startActivityForResult(intent, 0)
        } else {
            Toast.makeText(this, "Contraseña o correo incorrecto", Toast.LENGTH_LONG)
        }
    }

    fun txtWatch(txt: EditText) {
        txt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (!txtEmail.text.toString().equals("") && !txtPsw.text.toString().equals("")) {

                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }
}
