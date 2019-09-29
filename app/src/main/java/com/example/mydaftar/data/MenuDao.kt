package com.example.mydaftar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MenuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun tambahMakanan(makananModel: MenuMakananModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun tambahMinuman(minumanModel: MenuMinumanModel)

    @Query("Select * from MenuMakananModel")
    fun ambilMenuMakanan():LiveData<List<MenuMakananModel>>

    @Query("Select * from MenuMinumanModel")
    fun ambilMenuMinuman():LiveData<List<MenuMinumanModel>>
}