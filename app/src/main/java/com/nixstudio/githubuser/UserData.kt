package com.nixstudio.githubuser

import android.content.Context
import android.content.res.Resources
import androidx.annotation.RawRes
import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import java.io.StringReader

class UserData(context: Context) {

    private fun Resources.getRawTextFile(@RawRes id : Int) : String {
        return openRawResource(id).bufferedReader().use { it.readText() }
    }

    private val githubUser = context.resources.getRawTextFile(R.raw.githubuser)
    private val list = arrayListOf<User>()

    val listUser : ArrayList<User>
    get() {
        JsonReader(StringReader(githubUser)).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    val user = Klaxon().parse<User>(reader)
                    if (user != null) {
                        list.add(user)
                    }
                }

            }
        }

        return list
    }
}