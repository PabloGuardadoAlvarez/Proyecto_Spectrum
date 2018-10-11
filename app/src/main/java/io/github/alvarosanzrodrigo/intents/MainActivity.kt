package io.github.alvarosanzrodrigo.intents

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife

class MainActivity : AppCompatActivity() {
    //@BindView(R.id.editText2) lateinit var txtEmail : EditText
    lateinit var txtEmail: EditText
    lateinit var txtPsw: EditText
    companion object {
        public val KEY_USER_NAME = "foo"
        public  val KEY_USER_PASSWORD = "fii"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtEmail = findViewById(R.id.editText2)
        txtPsw = findViewById(R.id.editText3)
    }

    fun onGoPressed(view: View) {
        val intent = Intent(this@MainActivity, PersonalData::class.java)

       intent.putExtra(KEY_USER_NAME, txtEmail.text.toString())
        intent.putExtra(KEY_USER_PASSWORD, txtPsw.text.toString())
       startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == requestCode)
            Toast.makeText(this, "resultCode: $resultCode y requestCode $requestCode, y además son iguales", Toast.LENGTH_LONG).show()
         else
            Toast.makeText(this, "resultCode: $resultCode y requestCode $requestCode, y además son diferentes", Toast.LENGTH_LONG).show()
    }
}
