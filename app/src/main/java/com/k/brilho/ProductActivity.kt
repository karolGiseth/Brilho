package com.k.brilho

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.k.brilho.models.Product
import com.k.brilho.models.UserCar
import kotlinx.android.synthetic.main.activity_product.*

//actividad muestra todos los productos
class ProductActivity : AppCompatActivity() {
    private var dataB = BrilhoRoomDatabase.getDatabase(this)
    private lateinit var prefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        prefs = getSharedPreferences(getString(R.string.preferences_file), MODE_PRIVATE)
        title = "Productos"

        val isAdmin = prefs.getBoolean(getString(R.string.is_admin), false)

        val products = dataB.productDao().getProducts()

        //adaptador que organiza lo que se va a mostrar en cada item
        val adapter = AdapterProduct(products as ArrayList<Product>, this, !isAdmin)
        //pasa el adaptador a la lista a mostrar
        gridProduct.adapter = adapter
    }

    fun addToCar(i: Int) {
        val userId = prefs.getInt(getString(R.string.user_id), 0)
        val product = UserCar(userId, i, 1)
        val result = dataB.userCarDao().insertProduct(product)
        if (result.toInt() == -1) {
            val number = dataB.userCarDao().getProduct(product.idUser, product.idProduct)
            product.units = product.units + number
            dataB.userCarDao().updateProduct(product)
        }
    }

}
