package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao?

    companion object{
        private var INSTANCE: UserDatabase? = null

        fun getAppDatabase(context: Context): UserDatabase? {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, UserDatabase::class.java, "use.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }

}