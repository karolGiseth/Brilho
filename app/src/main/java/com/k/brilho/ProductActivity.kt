package com.k.brilho

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.k.brilho.models.Product
import kotlinx.android.synthetic.main.activity_product.*

//actividad muestra todos los productos
class ProductActivity : AppCompatActivity() {

    var listCar = arrayListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        title = "Productos"
        val prefs = getSharedPreferences(getString(R.string.preferences_file), MODE_PRIVATE)
        val isAdmin = prefs.getBoolean(getString(R.string.is_admin), false)
        //trae nombre las imagenes guardadas en una array
        val imagesOptions = resources.getStringArray(R.array.images)
        //lista quemada de todos los productos
        val products = arrayListOf(
            Product(1, "Manillas", 2000, imagesOptions[0]),
            Product(2, "Anillo", 3000, imagesOptions[1]),
            Product(3, "arete", 5000, imagesOptions[1])
        )
        //adaptador que organiza lo que se va a mostrar en cada item
        val adapter = AdapterProduct(products, this, !isAdmin)
        //pasa el adaptador a la lista a mostrar
        gridProduct.adapter = adapter
    }

    //si se oprime boton atras evuelve la lista de articulos añadidos al menu
    override fun onBackPressed() {
        getIntent().putExtra("car", listCar)
        setResult(1, intent)
        super.onBackPressed()
    }
}
