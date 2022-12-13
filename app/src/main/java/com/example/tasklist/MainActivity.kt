package com.example.tasklist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity() {

    private var switch: Switch? = null
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Teeman vaihto alla :)

        saveData = SaveData (this)

        if(saveData.loadDarkModeState() == true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        switch = findViewById<View>(R.id.switch_id) as Switch?
        if (saveData.loadDarkModeState() == true) {
            switch!!.isChecked = true
        }

        switch!!.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                saveData.setDarkModeState(true)
                restartApplication()
        }else {
                saveData.setDarkModeState(false)
                restartApplication()
            }
        }


        val btnNext = findViewById<Button>(R.id.btnNext)
        btnNext.setOnClickListener {
            val intent = Intent(this, TaskActivity::class.java)
            startActivity(intent)
        }

    }

    //Käynnistää applikaation uudelleen, jolloin lataa tallennetun teeman käyttöön

    private fun restartApplication() {
        val i = Intent(applicationContext, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}