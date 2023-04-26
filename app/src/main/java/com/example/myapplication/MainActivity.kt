package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.RoomDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var userAdapter: UserAdapter
    lateinit var listUser: ArrayList<User>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addUser.setOnClickListener{
            addUserDb()
        }

        deleteAll.setOnClickListener {
            listUser = arrayListOf()
            UserDatabase.getAppDatabase(this)?.userDao()?.deleteAllUser()
            userAdapter = UserAdapter(this,listUser)

            val linear: LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rcvUser.layoutManager = linear

            rcvUser.adapter = userAdapter
            userAdapter.notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addUserDb() {
        val name: String = edtUserName.text.toString().trim()
        val address: String = edtUserAddress.text.toString().trim()

        if(name.isEmpty() || address.isEmpty()){
            return;
        }

        val user = User(0,name, address)

        if(isUserExist(user)){
            Toast.makeText(this, "User exist", Toast.LENGTH_SHORT).show()
            return
        }

        UserDatabase.getAppDatabase(this)?.userDao()
            ?.insertUser(user)

        Toast.makeText(this,"Add user successfully", Toast.LENGTH_SHORT).show()

        edtUserName.setText("")
        edtUserAddress.setText("")

        hideSoftKeyboard()

        listUser = UserDatabase.getAppDatabase(this)?.userDao()?.getListUser() as ArrayList<User>
        userAdapter = UserAdapter(this,listUser)
        listUser = arrayListOf()

        val linear: LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcvUser.layoutManager = linear

        rcvUser.adapter = userAdapter
        userAdapter.notifyDataSetChanged()
    }

    private fun isUserExist(user: User) : Boolean{
        var list: ArrayList<User> = arrayListOf()
        list = user.nameUser?.let {
            UserDatabase.getAppDatabase(this)?.userDao()?.checkUser(it)
        } as ArrayList<User>

        return list.isNotEmpty()
    }

    private fun hideSoftKeyboard(){
        try {
            val inputMethodManager: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }catch (ex: java.lang.NullPointerException){
            ex.printStackTrace()
        }
    }
}