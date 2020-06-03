package com.k.brilho.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int?,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "password")
    var password: String? = null,
    @ColumnInfo(name = "age")
    var age: Int? = null,
    @ColumnInfo(name = "admin")
    var admin: Boolean = false,
    @ColumnInfo(name = "value")
    var value: Int = 0
) {
    @Ignore
    var listProducts = arrayListOf<UserProduct>()
}
