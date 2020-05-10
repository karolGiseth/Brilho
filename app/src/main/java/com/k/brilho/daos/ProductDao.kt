package com.k.brilho.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.k.brilho.models.Product

//ultima clase no se utiliza aun
//realizar manejo de BD
@Dao
interface ProductDao {

    @Query("SELECT * from products")
    fun getProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProduct(word: Product)

}