package io.github.alvarosanzrodrigo.intents

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast


class PersonalData : AppCompatActivity() {

    companion object {
        private val REQUEST_TAKE_PHOTO = 0
        private const val REQUEST_SELECT_IMAGE_IN_ALBUM = 1
        const val PROFILE_PIC = "absoluteProfilePicPath"
    }

    private lateinit var descriptionEditText: EditText
    private lateinit var nameTextView: TextView
    private lateinit var surnameTextView: TextView
    private lateinit var profileImageView: ImageView
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        bindView()
        //Here I make the description text view scrollable without scrollView for performance improvements
        descriptionEditText.movementMethod = ScrollingMovementMethod()
        loadDataFromIntentExtras()
    }

    // can't get ButterKnife working so I created a function myself for no overloading the onCreate() with this simple task
    private fun bindView() {
        descriptionEditText = findViewById(R.id.personal_data_description_editText)
        nameTextView = findViewById(R.id.personal_data_name_text_view)
        surnameTextView = findViewById(R.id.personal_data_surname_text_view)
        profileImageView = findViewById(R.id.image_picker_image_view)
    }

    // I made a function where I extract the data from the intent extras and show them in the view
    private fun loadDataFromIntentExtras() {
        nameTextView.text = intent?.getStringExtra("") ?: "Vacío"
    }

    //this creates the intent for loading a image from the galery
    fun onImageViewClicked(view: View) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)
        }
    }

    fun onSaveButtonClicked(view: View){
        //val intent = Intent(this, Pruebapic::class.java)
        //intent.putExtra("user", user)
        //startActivity(intent)
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
            profileImageView.setImageBitmap(bitmap)
            user = User()
            user.profileimage = imageUri
        }
    }
}

