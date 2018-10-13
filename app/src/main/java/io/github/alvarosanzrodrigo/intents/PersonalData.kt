package io.github.alvarosanzrodrigo.intents

import android.app.Activity
import android.graphics.Picture
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Parcelable
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.View
import android.widget.ImageView
import java.io.File
import android.R.attr.data
import android.graphics.drawable.Drawable
import android.provider.MediaStore
import android.graphics.Bitmap


class PersonalData : AppCompatActivity() {

    companion object {
        private val REQUEST_TAKE_PHOTO = 0
        private const val REQUEST_SELECT_IMAGE_IN_ALBUM = 1
    }

    private lateinit var descriptionTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var surnameTextView: TextView
    private lateinit var hola: String
    private lateinit var profileImageView: ImageView

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
        profileImageView = findViewById(R.id.image_picker_image_view)
    }

    // I made a function where I extract the data from the intent extras and show them in the view
    private fun loadDataFromIntentExtras() {
        nameTextView.text = intent?.let { intent ->
            intent.getStringExtra("")
        } ?: "Vacío"

    }

    //this creates the intent for loading a image from the galery
    fun onImageViewClicked(view: View) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, i: Intent?) {
        super.onActivityResult(requestCode, resultCode, i)
        if (resultCode === Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_SELECT_IMAGE_IN_ALBUM -> manageImageFromUri(i?.data!!)
            }
        } else {
            // manage result not ok !
        }

    }


    private fun manageImageFromUri(imageUri: Uri) {
        var bitmap: Bitmap? = null

        try {
            bitmap = MediaStore.Images.Media.getBitmap(
                    this.contentResolver, imageUri)

        } catch (e: Exception) {
            // Manage exception ...
        }

        if (bitmap != null) {
            // Here you can use bitmap in your application ...
            profileImageView.setImageBitmap(bitmap)
        }
    }


}

