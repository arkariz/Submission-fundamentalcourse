package com.arrkariz.githubconsumerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arrkariz.githubconsumerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ListUserAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListUserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        binding.apply {
            rvFav.setHasFixedSize(true)
            rvFav.layoutManager = LinearLayoutManager(this@MainActivity)
            rvFav.adapter = adapter
        }

        viewModel.setFavUser(this)

        viewModel.getFavoriteUser()?.observe(this, {
            if (it != null) {
                adapter.setList(it)
            }
        })
    }

}