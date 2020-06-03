package com.k.brilho

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.k.brilho.models.Product
import kotlinx.android.synthetic.main.activity_client.*

class ClientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)
        val dataB = BrilhoRoomDatabase.getDatabase(this)
        val users = dataB.userDao().getAllUsers()
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users).also { adapter ->
            searchUser.setAdapter(adapter)
        }
        searchUser.setOnItemClickListener { parent, view, position, id ->
            val user = dataB.userDao().getUser(searchUser.text.toString())

            val allProducts = dataB.productDao().getProducts()
            user.id?.let {
                val productsCar = dataB.userProductDao().getAllProductCar(it)
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
                adapter.notifyDataSetChanged()

                var total = 0
                //evalua y suma el total de lo que esta en el carro con los productos
                carProducts.forEach {
                    total += (it.value!! * it.quantity)
                }

                //setea campo total
                balance.text = resources.getString(R.string.total) + total.toString()
            }


        }

        userProductsList

    }
}
