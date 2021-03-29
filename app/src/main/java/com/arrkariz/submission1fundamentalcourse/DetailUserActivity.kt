package com.arrkariz.submission1fundamentalcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.arrkariz.submission1fundamentalcourse.databinding.ActivityDetailUserBinding
import com.arrkariz.submission1fundamentalcourse.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding

    companion object {
        const val EXTRA_USER = "extra_user"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<Userdata>(EXTRA_USER) as Userdata

        Glide.with(this)
            .load(user.photo)
            .apply(RequestOptions().override(350, 550))
            .into(binding.imgItemPhoto)

        binding.tvItemName.text = user.name
        binding.tvUsername.text = user.userName
        binding.follower.text = user.follower
        binding.following.text = user.following
        binding.tvCompany.text = user.company
        binding.repository.text = user.repository
        binding.tvLocation.text = user.location
    }
}