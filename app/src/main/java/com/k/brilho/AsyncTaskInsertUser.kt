package com.k.brilho

import android.os.AsyncTask
import com.k.brilho.daos.UserDao
import com.k.brilho.models.User

class AsyncTaskInsertUser(var dao: UserDao) : AsyncTask<User, String, Void>() {
    override fun doInBackground(vararg params: User): Void? {
        dao.insertUser(user = params.first())
        return null
    }
}