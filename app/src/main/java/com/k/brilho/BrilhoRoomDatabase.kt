package com.k.brilho

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.k.brilho.daos.ProductDao
import com.k.brilho.daos.UserCarDao
import com.k.brilho.daos.UserDao
import com.k.brilho.daos.UserProductDao
import com.k.brilho.models.Product
import com.k.brilho.models.User
import com.k.brilho.models.UserCar
import com.k.brilho.models.UserProduct

//ultima clase no se utiliza aun
//realizar la creacion y devuelve la instancia de la base de datos
@Database( exportSchema= false,
    entities = arrayOf(Product::class, User::class, UserProduct::class, UserCar::class),
    version = 3
)
abstract class BrilhoRoomDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao
    abstract fun userProductDao(): UserProductDao
    abstract fun userCarDao(): UserCarDao

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
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = db
                }
                insertInformation()

            }
            return INSTANCE!!
        }

        private fun insertInformation() {
            val user=INSTANCE!!.userDao().getUser("admin")
            if (user == null) {
                INSTANCE!!.userDao().insertUser(User(0, "admin", "admin", admin = true))
                INSTANCE!!.productDao().insertProduct(Product(1, "Manilla", 2000, "joya_uno", 3))
                INSTANCE!!.productDao().insertProduct(Product(2, "Anillo", 3000, "joya_dos", 2))
                INSTANCE!!.productDao().insertProduct(Product(3, "Arete", 5000, "joya_dos", 5))
            }

        }
    }
}