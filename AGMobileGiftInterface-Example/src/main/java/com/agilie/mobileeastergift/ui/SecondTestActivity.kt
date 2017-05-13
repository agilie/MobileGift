package com.agilie.mobileeastergift.ui

import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.agilie.mobileeastergift.R
import com.agilie.mobileeastergift.User
import com.agilie.mobileeastergift.UsersRVAdapter
import kotlinx.android.synthetic.main.activity_test.*


class SecondTestActivity : android.support.v7.app.AppCompatActivity(), UsersRVAdapter.AddNewUserListener {
    lateinit var usersRecyclerView: RecyclerView
    lateinit var userRVAdapter: UsersRVAdapter

    var userList: ArrayList<User> = ArrayList()


    companion object {
        fun getCallingIntent(context: android.content.Context) = Intent(context, SecondTestActivity::class.java)
    }

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.agilie.mobileeastergift.R.layout.activity_test)
        usersRecyclerView = usersRv
        var horizontalLayoutManagaer: LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        initUsersList()

        userRVAdapter = UsersRVAdapter(userList, this)
        usersRecyclerView.layoutManager = horizontalLayoutManagaer
        usersRecyclerView.adapter = userRVAdapter
    }


    fun initUsersList() {
        userList = ArrayList()
        userList.add(User("John", BitmapFactory.decodeResource(resources, R.drawable.john_pic_big)))
        userList.add(User("Olivia", BitmapFactory.decodeResource(resources, R.drawable.olivia_pic_big)))
        userList.add(User("Add", BitmapFactory.decodeResource(resources, R.mipmap.add_member)))
    }

    override fun addNewUser() {
        Log.d("testLog", "add new user listener")
    }
}