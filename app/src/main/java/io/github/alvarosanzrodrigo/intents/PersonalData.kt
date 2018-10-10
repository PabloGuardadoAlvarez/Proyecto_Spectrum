package io.github.alvarosanzrodrigo.intents

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView


class PersonalData : AppCompatActivity() {

    private lateinit var descriptionTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var surnameTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        bindView()
        //Here I make the description text view scrollable without scrollView for performance improvements
        descriptionTextView.movementMethod = ScrollingMovementMethod()

        loadDataFromIntentExtras()
    }

    // can't get ButterKnife working so I created a function myself for no overloading the onCreate() with this simple task
    private fun bindView() {
        descriptionTextView = findViewById(R.id.personal_data_description_text_view)
        nameTextView = findViewById(R.id.personal_data_name_text_view)
        surnameTextView = findViewById(R.id.personal_data_surname_text_view)
    }
    // I made a function where I extract the data from the intent extras and show them in the view
    private fun loadDataFromIntentExtras(){
        nameTextView.text = intent?.let { intent ->
            intent.getStringExtra("")
        } ?: "Vacío"

    }
}
