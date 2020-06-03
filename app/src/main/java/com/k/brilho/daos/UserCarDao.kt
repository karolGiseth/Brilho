package com.k.brilho.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.k.brilho.models.UserCar

@Dao
interface UserCarDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProduct(uc: UserCar): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateProduct(uc: UserCar): Long

    @Query("Select units from user_car where idUser=:user  and  idProduct=:product")
    fun getProduct(user: Int, product: Int): Int

    @Query("Select * from user_car where idUser=:user")
    fun getAllProductCar(user: Int): List<UserCar>


    @Query("DELETE FROM user_car WHERE idUser=:id")
    fun deleteCarUser(id: Int)
}