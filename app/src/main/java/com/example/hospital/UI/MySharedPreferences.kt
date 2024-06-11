package com.example.hospital.UI

import android.content.Context
import android.content.SharedPreferences


object MySharedPreferences {

    private const val SHARED_PREFERENCES_NAME = "hospital data"
    private var mAppContext: Context? = null
    private const val USER_TOKEN= "token"
    private const val NAME= "name"



    fun init(appContext: Context?) {
        mAppContext = appContext
    }

    private fun getSharedPreferences(): SharedPreferences {
        return mAppContext!!.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun setUserTOKEN (id : String){

        val editor = getSharedPreferences().edit()
        editor.putString(USER_TOKEN, id).apply()

    }

    fun getUserToken(): String? {
        return getSharedPreferences().getString(USER_TOKEN, "" )
    }
    fun setUserName(id : String){

        val editor = getSharedPreferences().edit()
        editor.putString(NAME, id).apply()

    }

    fun getUserName(): String? {
        return getSharedPreferences().getString(NAME, "" )
    }



}