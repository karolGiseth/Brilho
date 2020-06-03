package com.k.brilho

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*

//actividad contiene el menu
class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        title = "Menu"

        val prefs = getSharedPreferences(getString(R.string.preferences_file), MODE_PRIVATE)
        val isAdmin = prefs.getBoolean(getString(R.string.is_admin), false)
        if (isAdmin) {
            ll_main_layout3.visibility = View.GONE
            ll_main_layout4.visibility = View.GONE
        } else {
            ll_main_layout5.visibility = View.GONE
            ll_main_layout2.visibility = View.GONE
        }
        //boton inicia acividad mostrar todos los productos
        productsButton.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)
        }

        //boton inicia acividad carro de compras
        carButton.setOnClickListener {
            val intent = Intent(this, CarActivity::class.java)
            startActivity(intent)
        }

        //boton inicia acividad ver cliente y factura de el
        clientButton.setOnClickListener {
            val intent = Intent(this, ClientActivity::class.java)
            startActivity(intent)
        }
        //boton inicia acividad ver cliente y factura de el
        myProductButton.setOnClickListener {
            val intent = Intent(this, MyProductsActivity::class.java)
            startActivity(intent)
        }
        //boton inicia acividad ver cliente y factura de el
        addProdu.setOnClickListener {
            val intent = Intent(this, AddProdActivity::class.java)
            startActivity(intent)
        }
    }
}
