package com.arrkariz.githubconsumerapp


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arrkariz.githubconsumerapp.databinding.ItemRowUserBinding
import com.arrkariz.submission1fundamentalcourse.Userdata
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlin.collections.ArrayList

class ListUserAdapter : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {
    private val listUser = ArrayList<Userdata>()

    fun setList(users: ArrayList<Userdata>){
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = ItemRowUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
    }


    override fun getItemCount(): Int {
        return listUser.size
    }


    inner class ListViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Userdata){
            binding.apply {
                Glide.with(itemView)
                        .load(user.avatar_url)
                        .apply(RequestOptions().override(55, 55))
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imgItemPhoto)

                tvItemName.text = user.login
            }
        }

    }
}