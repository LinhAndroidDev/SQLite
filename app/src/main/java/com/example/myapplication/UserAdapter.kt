package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(val context: Context,val listUser: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewholder>() {

    class UserViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.txtName)
        val address: TextView = itemView.findViewById(R.id.txtAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewholder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_user,parent,false)
        return UserViewholder(itemView)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewholder, position: Int) {
        val user: User = listUser[position]
        holder.name.text = user.nameUser
        holder.address.text = user.address
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}