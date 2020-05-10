package com.k.brilho

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.k.brilho.models.User
import kotlinx.android.synthetic.main.activity_create_user.*

class CreateUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        title = "Create User"
        createUserNew.setOnClickListener {
            val name = userNew.text.toString()
            val password = passNew.text.toString()
            val age = ageNew.text.toString().toIntOrNull()
            if (name.isNotEmpty() && password.isNotEmpty()) {
                val user = User(null, name, password, age)
                val dataB = BrilhoRoomDatabase.getDatabase(this)
                val exist = dataB.userDao().userExist(user.name!!)
                if (exist == null) {
                    AsyncTaskInsertUser(dataB.userDao()).execute(user)
                    Toast.makeText(this, "Usuario Insertado", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                } else Toast.makeText(this, "Usuario ya existe", Toast.LENGTH_SHORT)
                    .show()
            } else Toast.makeText(this, "LLene los campos username y password", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
