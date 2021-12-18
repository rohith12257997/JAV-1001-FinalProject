package com.example.contact

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.HashSet

class SharedPreference(context: Context) {
    private var contact = "CONTACT_LIST"
    private val preference = context.getSharedPreferences(
        "SharedPreference", Context.MODE_PRIVATE
    )!!

    @SuppressLint("CommitPrefEdits")
    var set: HashSet<ContactModel> = HashSet()
    val gson = Gson()
    private val editor = preference.edit()

    //storing list to mobile memory
    fun setStateList(list: ArrayList<ContactModel>) {
        val json = gson.toJson(list)
        editor.putString(contact, json)
        editor.apply()
    }

    //getting list from mobile memory
    fun getStateList(): ArrayList<ContactModel>? {
        val json = preference.getString(contact, "")
        val type: Type = object : TypeToken<ArrayList<ContactModel?>>() {}.type
        return gson.fromJson(json, type)
    }
}