package com.example.mydaftar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MenuMakananModel (
    @PrimaryKey(autoGenerate = true)
    var idMenu:Int?=null,
    var namaMenu:String,
    var hargaMenu:String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var gambarMenu:ByteArray?
)