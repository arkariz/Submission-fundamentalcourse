package com.arrkariz.submission1fundamentalcourse

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Userdata(
    val photo: Int?,
    val name: String?,
    val userName: String?,
    val location: String?,
    val follower: String?,
    val following: String?,
    val company: String?,
    val repository: String?
): Parcelable