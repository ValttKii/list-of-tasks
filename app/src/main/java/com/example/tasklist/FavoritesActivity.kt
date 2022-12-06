package com.example.tasklist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasklist.databinding.FavoritesTodoBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: FavoritesTodoBinding
    private lateinit var favorites : List<Task>
    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FavoritesTodoBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(TaskViewModel::class.java)


        viewModel.allFavorites.observe(this){
            favorites = it
            binding.rvFavItems.adapter = FavAdapter(favorites)
        }

        setContentView(binding.root)

        binding.rvFavItems.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
        }
    }
}