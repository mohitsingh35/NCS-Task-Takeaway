package com.ncs.ncstakeawaytask

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

object PrefManager {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            "myPrefs",
            Context.MODE_PRIVATE
        )
        editor = sharedPreferences.edit()
    }

    fun setcurrentUserdetails(user:User){
        editor.putString("email",user.email)
        editor.putString("pass",user.password)
        editor.putString("name",user.name)
        editor.putString("bio",user.bio)
        editor.putString("phNum",user.phoneNum)
        editor.putBoolean("detailsAdded",user.detailsAdded)
        editor.apply()
    }

    fun getcurrentUserdetails():User{

        val name = sharedPreferences.getString("name", "")
        val email = sharedPreferences.getString("email", "")
        val bio = sharedPreferences.getString("bio", "")
        val phNum = sharedPreferences.getString("phNum", "")
        val pass = sharedPreferences.getString("pass", "")
        val details = sharedPreferences.getBoolean("detailsAdded", false)

        return User(email = email!!,name=name!!, bio = bio!!, phoneNum = phNum!!, password = pass!!, detailsAdded = details)
    }

    fun setUserDetails(user: User) {
        val userDetailsMap = getUserDetailsMap()
        userDetailsMap[user.email] = user
        saveUserDetailsMap(userDetailsMap)
    }

    fun getUserDetails(email: String): User? {
        val userDetailsMap = getUserDetailsMap()
        return userDetailsMap[email]
    }

    fun getAllUsers(): List<User> {
        val userDetailsMap = getUserDetailsMap()
        return userDetailsMap.values.toList()
    }

    private fun getUserDetailsMap(): HashMap<String, User> {
        val userDetailsJson = sharedPreferences.getString("userDetails", "{}")
        val type = object : TypeToken<HashMap<String, User>>() {}.type
        return Gson().fromJson(userDetailsJson, type)
    }

    private fun saveUserDetailsMap(userDetailsMap: HashMap<String, User>) {
        val userDetailsJson = Gson().toJson(userDetailsMap)
        editor.putString("userDetails", userDetailsJson)
        editor.apply()
    }


}