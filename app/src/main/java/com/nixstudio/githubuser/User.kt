package com.nixstudio.githubuser

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var username : String = "",
    var name : String = "",
    var avatar : String = "",
    var company : String = "",
    var location : String = "",
    var repository : Int? = 0,
    var follower : Int? = 0,
    var following : Int? = 0
) : Parcelable
