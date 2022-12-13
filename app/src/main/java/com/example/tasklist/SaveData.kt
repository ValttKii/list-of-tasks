package com.example.tasklist

import android.content.Context
import android.content.SharedPreferences

class SaveData (context: Context) {
    private var sharedPreferences: SharedPreferences = context.getSharedPreferences("file", Context.MODE_PRIVATE)

    // Tämä tallentaa yö moodi statuksen :TRUE / :FALSE

    fun setDarkModeState(state: Boolean?) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("Dark", state!!)
        editor.apply()
    }

    //Tämä lataa yö moodin

    fun loadDarkModeState(): Boolean? {
        val state = sharedPreferences.getBoolean("Dark", false)
        return (state)
    }



}