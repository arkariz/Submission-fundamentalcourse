package com.arrkariz.submission1fundamentalcourse.view


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.arrkariz.submission1fundamentalcourse.R
import com.arrkariz.submission1fundamentalcourse.Userdata
import com.arrkariz.submission1fundamentalcourse.databinding.ItemRowUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlin.collections.ArrayList

class ListUserAdapter : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val listUser = ArrayList<Userdata>()

    fun setList(users: ArrayList<Userdata>){
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
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

                itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[adapterPosition]) }
            }
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Userdata)
    }
}