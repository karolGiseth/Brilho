package com.k.brilho

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*

//actividad que contiene el login
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BrilhoRoomDatabase.getDatabase(this)
        Stetho.initializeWithDefaults(this)
        setContentView(R.layout.activity_main)
        //si boton es oprimido
        login.setOnClickListener {
            //validacion usuario y contraseña
            val dataB = BrilhoRoomDatabase.getDatabase(this)
            val user = dataB.userDao().login(userName.text.toString(), pass.text.toString())

            if (user != null) {
                val editor = getSharedPreferences(
                    getString(R.string.preferences_file),
                    Context.MODE_PRIVATE
                ).edit()
                editor.putBoolean(getString(R.string.is_admin), user.admin)
                editor.apply()
                //inicia actividad menu
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            } else {
                //mensaje error login
                pass.text.clear()
                Toast.makeText(this, "Error en el usuario o contraseña", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        createUser.setOnClickListener {
            val intent = Intent(this, CreateUserActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        //borra los campos
        userName.text.clear()
        pass.text.clear()
    }
}
