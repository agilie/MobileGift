package com.agilie.mobileeastergift

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_user.view.*


class UsersRVAdapter(var userList: ArrayList<User>, var addNewUserListener: AddNewUserListener) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindData(userList[position].name, userList[position].image, userList.lastIndex == position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int)
            = ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.layout_user, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(name: String, image: Bitmap, lastPosition: Boolean) {
            itemView.userName.text = name
            itemView.userImage.setImageBitmap(image)

            if (lastPosition) {
                itemView.userItemLayout.setOnClickListener { addNewUserListener.addNewUser() }
            }
        }
    }

    interface AddNewUserListener {
        fun addNewUser()
    }
}