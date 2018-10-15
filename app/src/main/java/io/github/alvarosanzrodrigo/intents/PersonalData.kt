package io.github.alvarosanzrodrigo.intents

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.*
import android.widget.Toast
import android.widget.RadioButton


class PersonalData : AppCompatActivity() {

    companion object {
        private val REQUEST_TAKE_PHOTO = 0
        private const val REQUEST_SELECT_IMAGE_IN_ALBUM = 1
        const val PROFILE_PIC = "absoluteProfilePicPath"
    }

    private lateinit var descriptionEditText: EditText
    private lateinit var nameTextView: TextView
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var surnameTextView: TextView
    private lateinit var profileImageView: ImageView
    private lateinit var descriptionCounter: TextView
    private lateinit var checkBoxMovies: CheckBox
    private lateinit var checkBoxHiking: CheckBox
    private lateinit var checkBoxSports: CheckBox
    private lateinit var checkBoxFriends: CheckBox
    private lateinit var checkBoxDancing: CheckBox
    private lateinit var checkBoxProgramming: CheckBox

    private lateinit var user: User
    private var hobbiesArrayList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        bindView()
        user = intent?.extras?.getParcelable(User.intentKey) as User
        //Here I make the description text view scrollable without scrollView for performance improvements
        descriptionEditText.movementMethod = ScrollingMovementMethod()
        loadDataFromUser()
        addListeners()
    }

    // can't get ButterKnife working so I created a function myself for no overloading the onCreate() with this simple task
    private fun bindView() {
        descriptionEditText = findViewById(R.id.personal_data_description_editText)
        nameTextView = findViewById(R.id.personal_data_name_text_view)
        surnameTextView = findViewById(R.id.personal_data_surname_text_view)
        profileImageView = findViewById(R.id.image_picker_image_view)
        radioGroupGender = findViewById(R.id.personal_data_gender_radiobutton)
        descriptionCounter = findViewById(R.id.descriptionCount)
        checkBoxMovies = findViewById(R.id.checkBoxMovies)
        checkBoxHiking = findViewById(R.id.checkBoxHiking)
        checkBoxSports = findViewById(R.id.checkBoxSports)
        checkBoxFriends = findViewById(R.id.checkBoxFriends)
        checkBoxDancing = findViewById(R.id.checkBoxDancing)
        checkBoxProgramming = findViewById(R.id.checkBoxProgramming)
    }

    // I made a function where I extract the data from the intent extras and show them in the view
    private fun loadDataFromUser() {
        nameTextView.text = user.name
        surnameTextView.text = user.surname

    }

    //this creates the intent for loading a image from the galery
    fun onImageViewClicked(view: View) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)
        }
    }

    fun onSaveButtonClicked(view: View) {
        user.hobbies = ""
        for (s in hobbiesArrayList) {
            user.hobbies += " ${s.toString()},"
            println(user.hobbies)
        }
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    // here we decide what to do from the resultCode
    override fun onActivityResult(requestCode: Int, resultCode: Int, i: Intent?) {
        super.onActivityResult(requestCode, resultCode, i)
        if (resultCode === Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_SELECT_IMAGE_IN_ALBUM -> manageImageFromUri(i?.data!!)
            }
        } else {
            Toast.makeText(this, "Error uploading photo", Toast.LENGTH_SHORT).show()
        }

    }

    // here I put the image on the imageView and save it on the resources for later
    private fun manageImageFromUri(imageUri: Uri) {
        var bitmap: Bitmap? = null

        try {
            bitmap = MediaStore.Images.Media.getBitmap(
                    this.contentResolver, imageUri)

        } catch (e: Exception) {
            Toast.makeText(this, "Error uploading photo", Toast.LENGTH_SHORT).show()
        }

        if (bitmap != null) {
            val round = RoundedBitmapDrawableFactory.create(resources, bitmap)
            round.cornerRadius = 25f
            profileImageView.setImageDrawable(round)
            user.profileimage = imageUri
        }
    }

    private fun onDescriptionWritingfinished() {
        user.description = descriptionEditText.text.toString()
    }

    private fun onGenderSelected() {
        val selectedID = radioGroupGender.checkedRadioButtonId
        var radioSexSelected: RadioButton = findViewById(selectedID)
        user.isFemale = radioSexSelected.text != "Male"
    }

    private fun onCheckBoxSelected(isChecked: Boolean, checkBox: CheckBox) {
        if (isChecked) hobbiesArrayList.add(checkBox.text.toString())
        else hobbiesArrayList.remove(checkBox.text.toString())
        Toast.makeText(this, hobbiesArrayList.toString(), Toast.LENGTH_LONG).show()
    }

    private fun addListeners() {
        descriptionEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                onDescriptionWritingfinished()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val length = "Description length: " + descriptionEditText.text.length.toString()
                descriptionCounter.text = length
            }
        })

        radioGroupGender.setOnClickListener {
                onGenderSelected()
            }

        checkBoxMovies.setOnCheckedChangeListener { buttonView, isChecked ->
            onCheckBoxSelected(isChecked, checkBoxMovies)

        }

        checkBoxHiking.setOnCheckedChangeListener { buttonView, isChecked ->
            onCheckBoxSelected(isChecked, checkBoxHiking)
        }

        checkBoxSports.setOnCheckedChangeListener { buttonView, isChecked ->
            onCheckBoxSelected(isChecked, checkBoxSports)
        }

        checkBoxFriends.setOnCheckedChangeListener { buttonView, isChecked ->
            onCheckBoxSelected(isChecked, checkBoxFriends)
        }

        checkBoxDancing.setOnCheckedChangeListener { buttonView, isChecked ->
            onCheckBoxSelected(isChecked, checkBoxDancing)
        }

        checkBoxProgramming.setOnCheckedChangeListener { buttonView, isChecked ->
            onCheckBoxSelected(isChecked, checkBoxProgramming)
        }

    }
}

