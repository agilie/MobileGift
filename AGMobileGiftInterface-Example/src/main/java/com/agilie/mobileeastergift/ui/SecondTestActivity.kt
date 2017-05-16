package com.agilie.mobileeastergift.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.agilie.agmobilegiftinterface.gravity.GravityController
import com.agilie.agmobilegiftinterface.gravity.GravityControllerImpl
import com.agilie.mobileeastergift.R
import com.agilie.mobileeastergift.User
import com.agilie.mobileeastergift.UsersAdapter
import kotlinx.android.synthetic.main.activity_test.*


class SecondTestActivity : AppCompatActivity(), UsersAdapter.AddNewUserListener {

    lateinit var userAdapter: UsersAdapter
    var userList: List<User> = getUsersList()

    var gravityController: GravityController? = null

    companion object {
        fun getCallingIntent(context: android.content.Context) = Intent(context, SecondTestActivity::class.java)
    }

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        userAdapter = UsersAdapter(userList, this, this)
        usersRecyclerView.adapter = userAdapter

        prepareActionBar()

        gravityController = GravityControllerImpl(this, rootScrollView)
    }

    override fun addNewUser() {
        Log.d("testLog", "add new user listener")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_stub, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_enable_physics -> gravityController?.start()
            R.id.action_disable_physics -> gravityController?.stop()
        }

        return super.onOptionsItemSelected(item)
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

    fun getUsersList(): List<User> {
        return listOf(
                User("John", R.drawable.john_pic_big),
                User("Olivia", R.drawable.olivia_pic_big),
                User("Add", R.drawable.add_member)
        )
    }
}