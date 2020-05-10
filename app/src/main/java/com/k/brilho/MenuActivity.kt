package com.k.brilho

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*

//actividad contiene el menu
class MenuActivity : AppCompatActivity() {
    //lista id de productos en el carro
    var listCar = arrayListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        title = "Menu"

        val prefs = getSharedPreferences(getString(R.string.preferences_file), MODE_PRIVATE)
        val isAdmin = prefs.getBoolean(getString(R.string.is_admin), false)
        if (isAdmin) {
            ll_main_layout3.visibility = View.GONE
        } else {
            ll_main_layout2.visibility = View.GONE
        }
        //boton inicia acividad mostrar todos los productos
        productsButton.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            //espera resultado de los articulos seleccionados
            startActivityForResult(intent, 1)
        }

        //boton inicia acividad carro de compras
        carButton.setOnClickListener {
            val intent = Intent(this, CarActivity::class.java)
            //le pasa la lista de articulos a la actividad de carro de compras
            intent.putExtra("car", listCar)
            startActivity(intent)
        }
        //boton inicia acividad carro de compras
        clientButton.setOnClickListener {
            val intent = Intent(this, ClientActivity::class.java)
            startActivity(intent)
        }
    }

    //cuando se finaliza la actividad todos los productos devuelve los productos seleccionados y se
    //agregan a la lista de esta actividad
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //para saber que viene de la actividad de todos los productos
        if (requestCode == 1 && resultCode == 1) {
            listCar.addAll(data?.getIntegerArrayListExtra("car") as ArrayList<Int>)
        }

    }
}
