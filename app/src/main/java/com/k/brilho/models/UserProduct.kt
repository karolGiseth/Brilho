package com.k.brilho.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "user_products",
    primaryKeys = arrayOf("idUser", "idProduct"),
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idUser"),
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = Product::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("idProduct"),
            onDelete = ForeignKey.CASCADE
        )]
)
class UserProduct(
    @ColumnInfo(name = "idUser")
    var idUser: Int,
    @ColumnInfo(name = "idProduct")
    var idProduct: Int,
    @ColumnInfo(name = "units")
    var units: Int = 1
)