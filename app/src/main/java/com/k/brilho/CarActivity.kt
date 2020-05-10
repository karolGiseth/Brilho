package com.k.brilho

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.k.brilho.models.Product
import kotlinx.android.synthetic.main.activity_car.*

//actividad administra gestion carro de compras
class CarActivity : AppCompatActivity() {

    var listCar = arrayListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)
        listCar.addAll(intent.getIntegerArrayListExtra("car"))

        title = "Carro de Compras"
        //carga nombres de las imagenes
        val imagesOptions = resources.getStringArray(R.array.images)
        //datos quemados de productos no BD
        val products = arrayListOf(
            Product(
                1,
                "Manillas",
                2000,
                imagesOptions[0],
                listCar.filter { it == 1 }.size
            ),
            Product(
                2,
                "Anillo",
                3000,
                imagesOptions[1],
                listCar.filter { it == 2 }.size
            ),
            Product(
                3,
                "arete",
                5000,
                imagesOptions[1],
                listCar.filter { it == 3 }.size
            )
        )
        //inicia el adaptador para generar los elemntos a mostrar en la UI
        val adapter =
            AdapterProduct(products.filter { it.quantity != 0 } as ArrayList<Product>, this, false)
        var total = 0
        //evalua y suma el total de lo que esta en el carro con los productos
        products.forEach {
            total += (it.value!! * it.quantity)
        }
        //setea campo total
        totalText.text = resources.getString(R.string.total) + total.toString()
        //pasa el adaptador a mostrar en la vista
        carProductsList.adapter = adapter
    }
}
