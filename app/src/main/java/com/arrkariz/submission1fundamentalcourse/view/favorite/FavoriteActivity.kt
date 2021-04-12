package com.arrkariz.submission1fundamentalcourse.view.favorite

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arrkariz.submission1fundamentalcourse.Userdata
import com.arrkariz.submission1fundamentalcourse.data.local.FavoriteUser
import com.arrkariz.submission1fundamentalcourse.databinding.ActivityFavoriteBinding
import com.arrkariz.submission1fundamentalcourse.view.main.ListUserAdapter
import com.arrkariz.submission1fundamentalcourse.view.detail.DetailUserActivity

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: ListUserAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListUserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        adapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Userdata) {
                val moveWithObjectIntent = Intent(this@FavoriteActivity, DetailUserActivity::class.java)
                moveWithObjectIntent.putExtra(DetailUserActivity.EXTRA_USER, data.login)
                moveWithObjectIntent.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                moveWithObjectIntent.putExtra(DetailUserActivity.EXTRA_AVATAR, data.avatar_url)
                startActivity(moveWithObjectIntent)
            }
        })

        binding.apply {
            rvFav.setHasFixedSize(true)
            rvFav.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvFav.adapter = adapter
        }

        viewModel.getFavoriteUser()?.observe(this, {
            if (it != null){
                val list = listMap(it)
                adapter.setList(list)
            }
        })
    }

    private fun listMap(users: List<FavoriteUser>): ArrayList<Userdata> {
        val listUser = ArrayList<Userdata>()
        for (user in users){
            val  userMapped = Userdata(
                user.avatar_url,
                user.login,
                user.id
            )
            listUser.add(userMapped)
        }
        return listUser
    }
}