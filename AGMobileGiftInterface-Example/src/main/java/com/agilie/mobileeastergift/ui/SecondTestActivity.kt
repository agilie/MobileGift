package com.agilie.mobileeastergift.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import com.agilie.mobileeastergift.R
import com.agilie.mobileeastergift.User
import com.agilie.mobileeastergift.UsersAdapter
import kotlinx.android.synthetic.main.activity_test.*


class SecondTestActivity : AppCompatActivity(), UsersAdapter.AddNewUserListener {
    lateinit var userAdapter: UsersAdapter
    var userList: ArrayList<User> = ArrayList()

    companion object {
        fun getCallingIntent(context: android.content.Context) = Intent(context, SecondTestActivity::class.java)
    }

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.agilie.mobileeastergift.R.layout.activity_test)
        prepareActionBar()
        getUsersList()
        userAdapter = UsersAdapter(userList, this, this)
        usersRv.adapter = userAdapter
    }

    override fun addNewUser() {
        Log.d("testLog", "add new user listener")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_stub, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun prepareActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun getUsersList() {
        userList.add(User("John", R.drawable.john_pic_big))
        userList.add(User("Olivia", R.drawable.olivia_pic_big))
        userList.add(User("Add", R.drawable.add_member))
    }


}