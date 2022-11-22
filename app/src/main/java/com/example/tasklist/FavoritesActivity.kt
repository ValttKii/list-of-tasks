package com.example.tasklist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.favorites_todo.*
import kotlinx.android.synthetic.main.item_favorite.*

class FavoritesActivity : AppCompatActivity() {

    private lateinit var favoAdapter: FavAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorites_todo)
        favoAdapter = FavAdapter(mutableListOf())

        rvFavItems.adapter = favoAdapter
        rvFavItems.layoutManager = LinearLayoutManager(this)


    }
}