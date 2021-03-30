package com.arrkariz.submission1fundamentalcourse

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arrkariz.submission1fundamentalcourse.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    private val list = ArrayList<Userdata>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)

        binding.btnSearch.setOnClickListener{
            searchUser()
        }

        binding.etQuery.setOnKeyListener{v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                searchUser()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        viewModel.getSearchUsers().observe(this, {
            if (it!=null){
                list.clear()
                list.addAll(it)
                showLoading(false)
            }
        })

        binding.rvUser.setHasFixedSize(true)
        showRecyclerList()
    }

    private fun searchUser(){
        binding.apply {
            val query = etQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.setSearchUser(query)
        }
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun showRecyclerList() {
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(list)
        binding.rvUser.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Userdata) {
                val moveWithObjectIntent = Intent(this@MainActivity, DetailUserActivity::class.java)
                moveWithObjectIntent.putExtra(DetailUserActivity.EXTRA_USER, data)
                startActivity(moveWithObjectIntent)
            }
        })
    }
}