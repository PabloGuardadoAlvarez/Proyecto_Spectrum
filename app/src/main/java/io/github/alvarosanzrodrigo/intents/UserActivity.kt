package io.github.alvarosanzrodrigo.intents

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val message: String = intent?.let { intent ->
            intent.getStringExtra(MainActivity.KEY_USER_NAME)
        } ?: ""
        setResult(0)
    }
}
