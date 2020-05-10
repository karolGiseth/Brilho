package com.k.brilho

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.k.brilho.models.Product

//adaptador que organiza los ittems que se van a mostrar tanto en todos los productos como en carro de productos (generar dinamicamente los elementos UI)
//allProducts esverdadero para la actividad de mosrrar todos los productos y false para cuando la llama el carro
class AdapterProduct(
    var list: ArrayList<Product>,
    var contextContent: Context?,
    var allProducts: Boolean
) :
    BaseAdapter() {
    //por cada objeto en la lista llama esta funcion
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        //aqui determina el layout que va a utilizar en cada elemento
        if (view == null) {
            view = LayoutInflater.from(contextContent)
                .inflate(R.layout.layout_product, parent, false)
        }
        //este es el elemento de la lista que entra (list)
        val currentItem = getItem(position)
        //toda esta parte encuentra el elemento del xml y le setea los valores correspondientes como nombre e imagen valor, cantidad
        val add = view!!.findViewById(R.id.addCar) as Button
        val image = view.findViewById(R.id.imageProduct) as ImageButton
        val tittle = view.findViewById(R.id.textProduct) as TextView
        val value = view.findViewById(R.id.textValue) as TextView
        val quantity = view.findViewById(R.id.textQuantity) as TextView
        //nombre de producto
        tittle.text = currentItem.name
        //precio del producto
        value.text = currentItem.value.toString()
        //asigna imagen
        image.setImageResource(
            contextContent?.resources!!.getIdentifier(
                currentItem.image,
                "drawable",
                contextContent?.packageName
            )
        )
        //dependiendo si es para generar los elementos de todos los productos o carro de compras // muestra o no alguna informacion
        if (allProducts) {
            //pone visible el boton agregar
            add.visibility = View.VISIBLE
            add.tag = currentItem.id
            add.setOnClickListener {
                //cuando se oprime añadir se agrega el id del elemento seleccionado a la lista de carro de la actividad todos los productos
                (contextContent as ProductActivity).listCar.add(it.tag as Int)
            }
        } else {
            //pone visiocultoble el boton agregar
            add.visibility = View.GONE
            quantity.setText("cantidad: " + currentItem.quantity)
        }
        return view
    }

    override fun getItem(position: Int): Product {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }
}