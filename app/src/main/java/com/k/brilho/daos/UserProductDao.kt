package com.k.brilho.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.k.brilho.models.UserCar
import com.k.brilho.models.UserProduct

@Dao
interface UserProductDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userProduct: UserProduct)

    @Query("SELECT units FROM user_products  WHERE idUser=:idUser  AND idProduct=:idProduct ")
    fun getUserCount(idUser: Int, idProduct: Int): Int

    @Query("Select * from user_products where idUser=:user")
    fun getAllProductCar(user: Int): List<UserProduct>

}