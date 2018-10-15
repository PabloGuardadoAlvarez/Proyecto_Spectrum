package io.github.alvarosanzrodrigo.intents

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import java.util.*

class User() : Parcelable{
    var profileimage: Uri? = null
    var email: String? = null
    var password: String? = null
    var name: String? = null
    var surname: String? = null
    var surname2: String? = null
    var adress: String? = null
    var postalCode: String? = null
    var city: String? = null
    var phone: Int = 0
    var phoneType: String? = null
    var description: String? = null
    var isFemale: Boolean = false
    var hobies: ArrayList<String>? = null
    
    constructor(parcel: Parcel) : this() {
        profileimage = parcel.readParcelable(Uri::class.java.classLoader)
        email = parcel.readString()
        password = parcel.readString()
        name = parcel.readString()
        surname = parcel.readString()
        surname2 = parcel.readString()
        adress = parcel.readString()
        postalCode = parcel.readString()
        city = parcel.readString()
        phone = parcel.readInt()
        phoneType = parcel.readString()
        description = parcel.readString()
        isFemale = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(profileimage, flags)
        parcel.writeString(email)
        parcel.writeString(password)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(surname2)
        parcel.writeString(adress)
        parcel.writeString(postalCode)
        parcel.writeString(city)
        parcel.writeInt(phone)
        parcel.writeString(phoneType)
        parcel.writeString(description)
        parcel.writeByte(if (isFemale) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        const val intentKey = "USER_OBJECT"
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }


}
