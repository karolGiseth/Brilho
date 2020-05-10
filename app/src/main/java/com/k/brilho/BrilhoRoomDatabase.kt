package com.k.brilho

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.k.brilho.daos.ProductDao
import com.k.brilho.daos.UserDao
import com.k.brilho.daos.UserProductDao
import com.k.brilho.models.Product
import com.k.brilho.models.User
import com.k.brilho.models.UserProduct

//ultima clase no se utiliza aun
//realizar la creacion y devuelve la instancia de la base de datos
@Database(entities = arrayOf(Product::class, User::class, UserProduct::class), version = 1)
abstract class BrilhoRoomDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao
    abstract fun userProductDao(): UserProductDao

    companion object {
        var INSTANCE: BrilhoRoomDatabase? = null

        //Obtener instancia base de datos
        fun getDatabase(context: Context): BrilhoRoomDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    val db = Room.databaseBuilder(
                        context.applicationContext,
                        BrilhoRoomDatabase::class.java, "brilho_db"
                    ).allowMainThreadQueries()
                        .build()
                    INSTANCE = db
                }
                INSTANCE!!.userDao().insertUser(User(0, "admin", "admin", admin = true))
            }
            return INSTANCE!!
        }
    }
}