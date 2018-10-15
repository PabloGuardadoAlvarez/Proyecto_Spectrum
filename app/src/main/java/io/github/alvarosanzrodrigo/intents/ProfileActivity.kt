package io.github.alvarosanzrodrigo.intents


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView


class ProfileActivity : AppCompatActivity() {

    private  lateinit var profilePicImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var surnamesTextView: TextView
    private lateinit var ageGenderTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var postalCodeTextView: TextView
    private lateinit var cityTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var phoneTypeTextView: TextView
    private lateinit var hobbiesTextView: TextView
    private lateinit var emailTextView: TextView

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = intent?.extras?.getParcelable("user") as User
        setContentView(R.layout.activity_profile)
        bindView()
        loadData()
    }

    private fun bindView(){
        profilePicImageView = findViewById(R.id.profilePictureProfileActivity)
        nameTextView = findViewById(R.id.nameTextView)
        surnamesTextView = findViewById(R.id.surnamesTextView)
        ageGenderTextView = findViewById(R.id.ageGenderTextView)
        descriptionTextView = findViewById(R.id.profileActivityDescriptionTextView)
        addressTextView = findViewById(R.id.address_textView)
        postalCodeTextView = findViewById(R.id.postal_code_textView)
        cityTextView = findViewById(R.id.city_textView)
        phoneTextView = findViewById(R.id.phoneTextView)
        phoneTypeTextView = findViewById(R.id.phoneTypeTextView)
        hobbiesTextView = findViewById(R.id.hobbiesTextView)
        emailTextView = findViewById(R.id.emailTextView)
    }


    private fun loadData (){
        profilePicImageView.setImageURI(user.profileimage)
        nameTextView.text = user.name
        surnamesTextView.text = user.surname + " " + user.surname2
        descriptionTextView.text = user.description
        addressTextView.text = user.address
        postalCodeTextView.text = user.postalCode
        cityTextView.text = user.city
        phoneTextView.text = user.phone.toString()
        phoneTypeTextView.text = user.phoneType
        hobbiesTextView.text = user.hobbies
        emailTextView.text = user.email

        if (user.isFemale){
            ageGenderTextView.text = getString(R.string.female)
        }else
            ageGenderTextView.text = getString(R.string.male)


    }
}
