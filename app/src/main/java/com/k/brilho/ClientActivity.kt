package com.k.brilho

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
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

    }
}
