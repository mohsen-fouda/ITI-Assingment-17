package com.Mohsin.ITI.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.Mohsin.ITI.OnClick
import com.Mohsin.ITI.model.User
import com.mohsin.iti.databinding.CustomPostsBinding

class PostsAdapter(
    private var userList: List<User>,
    private val listener: OnClick

) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {


    inner class PostsViewHolder(val binding: CustomPostsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(CustomPostsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val users = userList[position]
        holder.binding.apply {
            uName.text = "${users.firstName} ${users.lastName}"
            theDate.text = "${users.id}"
            PostContent.text = users.email
            Readbtn.setOnClickListener {
                listener.onClick(users, position)
            }
        }
        Glide.with(holder.itemView).load(users.avatar).into(holder.binding.imgView)

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}