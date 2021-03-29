package com.arrkariz.submission1fundamentalcourse

import android.os.Parcel
import android.os.Parcelable

data class Userdata(
    var photo: Int?,
    var name: String?,
    var userName: String?,
    var location: String?,
    var follower: String?,
    var following: String?,
    var company: String?,
    var repository: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(photo)
        parcel.writeString(name)
        parcel.writeString(userName)
        parcel.writeString(location)
        parcel.writeString(follower)
        parcel.writeString(following)
        parcel.writeString(company)
        parcel.writeString(repository)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Userdata> {
        override fun createFromParcel(parcel: Parcel): Userdata {
            return Userdata(parcel)
        }

        override fun newArray(size: Int): Array<Userdata?> {
            return arrayOfNulls(size)
        }
    }
}