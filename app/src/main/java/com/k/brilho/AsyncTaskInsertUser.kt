package com.k.brilho

import android.os.AsyncTask
import com.k.brilho.daos.UserCarDao
import com.k.brilho.daos.UserDao
import com.k.brilho.daos.UserProductDao
import com.k.brilho.models.Product
import com.k.brilho.models.User
import com.k.brilho.models.UserProduct

class AsyncTaskInsertUser(var dao: UserDao) : AsyncTask<User, String, Void>() {
    override fun doInBackground(vararg params: User): Void? {
        dao.insertUser(user = params.first())
        return null
    }
}

class AsyncTaskUpdateProductsUser(var dao: UserProductDao, var product: Product) :
    AsyncTask<User, String, Void>() {
    override fun doInBackground(vararg params: User): Void? {
        val up = UserProduct(params.first().id!!, product.id!!, product.quantity)
        val count = dao.getUserCount(up.idUser, up.idProduct)
        up.units += count
        dao.insertUser(up)
        return null
    }
}

class AsyncTaskDeleteProductsCar(var dao: UserCarDao) : AsyncTask<User, String, Void>() {
    override fun doInBackground(vararg params: User): Void? {
        dao.deleteCarUser(params.first().id!!)
        return null
    }
}