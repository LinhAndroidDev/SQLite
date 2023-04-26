package com.example.myapplication

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM user")
    fun getListUser(): List<User>

    @Query("SELECT * FROM user where name_user= :username")
    fun checkUser(username: String): List<User>

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM user")
    fun deleteAllUser()

    @Update
    fun updateUser(user: User)

}