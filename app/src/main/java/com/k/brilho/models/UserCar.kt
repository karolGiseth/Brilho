package com.k.brilho.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "user_car",
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
class UserCar(
    @ColumnInfo(name = "idUser")
    var idUser: Int,
    @ColumnInfo(name = "idProduct")
    var idProduct: Int,
    @ColumnInfo(name = "units")
    var units: Int = 1
)