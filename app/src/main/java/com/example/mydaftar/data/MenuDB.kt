package com.example.mydaftar.data

import android.content.Context
import android.view.Menu
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = arrayOf(MenuMakananModel::class, MenuMinumanModel::class), version = 2 )
abstract class MenuDB:RoomDatabase() {
    abstract fun menuDao():MenuDao

    companion object{
        var INSTANCE:MenuDB?=null
        fun getInstance(context: Context):MenuDB?{
        if(INSTANCE==null){ synchronized(MenuDB::class.java){
            INSTANCE= Room.databaseBuilder(
                context.applicationContext,
                MenuDB::class.java, "MenuDB.db"
            ).build()
        }}
            return INSTANCE
    }
        fun destroyInstance(){
        INSTANCE=null}
    }
}