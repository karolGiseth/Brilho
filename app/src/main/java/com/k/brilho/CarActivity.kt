package com.k.brilho

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.k.brilho.models.Product
import com.k.brilho.models.User
import kotlinx.android.synthetic.main.activity_car.*

//actividad administra gestion carro de compras
class CarActivity : AppCompatActivity() {
    private val dataB = BrilhoRoomDatabase.getDatabase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)
        val prefs = getSharedPreferences(getString(R.string.preferences_file), MODE_PRIVATE)
        val userId = prefs.getInt(getString(R.string.user_id), 0)

        val allProducts = dataB.productDao().getProducts()
        val productsCar = dataB.userCarDao().getAllProductCar(userId)

        title = "Carro de Compras"

        val carProducts = arrayListOf<Product>()
        productsCar.forEach { product ->
            val productInclude = allProducts.first { product.idProduct == it.id }
            productInclude.quantity = product.units
            carProducts.add(productInclude)
        }


        //inicia el adaptador para generar los elemntos a mostrar en la UI
        val adapter = AdapterProduct(carProducts, this, false)
        var total = 0
        //evalua y suma el total de lo que esta en el carro con los productos
        carProducts.forEach {
            total += (it.value!! * it.quantity)
        }

        //setea campo total
        totalText.text = resources.getString(R.string.total) + total.toString()
        //pasa el adaptador a mostrar en la vista
        carProductsList.adapter = adapter

        payButton.setOnClickListener {
            carProducts.forEach {
                AsyncTaskUpdateProductsUser(dataB.userProductDao(), it).execute(User(id = userId))
            }
            carProducts.clear()
            adapter.notifyDataSetChanged()
            AsyncTaskDeleteProductsCar(dataB.userCarDao())
                .execute(User(id = userId))
            totalText.text = resources.getString(R.string.total) + " 0"
            Toast.makeText(this, "Compra realizada exitosamente", Toast.LENGTH_SHORT).show()
        }
    }
}
