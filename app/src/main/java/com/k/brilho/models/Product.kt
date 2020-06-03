package com.k.brilho.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//modelo de producto utilizado
//mapeado con los campos de base de datos no se utiliza aun
@Entity(tableName = "products")
class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int?,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "value")
    var value: Int? = null,
    @ColumnInfo(name = "image")
    var image: String? = null,
    @ColumnInfo(name = "quantity")
    var quantity: Int = 0
)