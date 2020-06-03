package com.k.brilho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.k.brilho.models.Product
import com.k.brilho.models.User
import kotlinx.android.synthetic.main.activity_my_products.*

class MyProductsActivity : AppCompatActivity() {
    private val dataB = BrilhoRoomDatabase.getDatabase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_products)
        val prefs = getSharedPreferences(getString(R.string.preferences_file), MODE_PRIVATE)
        val userId = prefs.getInt(getString(R.string.user_id), 0)

        val allProducts = dataB.productDao().getProducts()
        val productsCar = dataB.userProductDao().getAllProductCar(userId)

        val carProducts = arrayListOf<Product>()
        productsCar.forEach { product ->
            val productInclude = allProducts.first { product.idProduct == it.id }
            productInclude.quantity = product.units
            carProducts.add(productInclude)
        }

        //inicia el adaptador para generar los elemntos a mostrar en la UI
        val adapter = AdapterProduct(carProducts, this, false)

        //pasa el adaptador a mostrar en la vista
        userProductsList.adapter = adapter

    }

}