package com.k.brilho

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.k.brilho.models.Product
import kotlinx.android.synthetic.main.activity_add_prod.*


class AddProdActivity : AppCompatActivity() {
    var imagePath: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_prod)
        val dataB = BrilhoRoomDatabase.getDatabase(this)

        addProduDB.setOnClickListener {
            val name = nameProdu.text
            val value = valueProdu.text
            val quanty = quantyProdu.text

            if (name.isNotEmpty() && value.isNotEmpty() && quanty.isNotEmpty() && imagePath != null) {
                val pro = Product(
                    null,
                    name.toString(),
                    value.toString().toInt(),
                    imagePath,
                    quanty.toString().toInt()
                )

                dataB.productDao().insertProduct(pro)
                onBackPressed()
                Toast.makeText(this, "Nuevo Producto agregado", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this, "Todos los campos deben estar llenos", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        imageAdd.setOnClickListener {
            val galleryIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            galleryIntent.type = "image/*"
            startActivityForResult(galleryIntent, 100);

        }
        exitBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK && null != data) {
            val selectedImageUri = data.data
            imageAdd.setImageURI(selectedImageUri)
            val projection =
                arrayOf(MediaStore.Images.Media.DATA)
            val cursor: Cursor? =
                contentResolver.query(selectedImageUri!!, projection, null, null, null)
            cursor?.moveToFirst()
            val column_index: Int = cursor!!.getColumnIndex(projection[0])
            imagePath = cursor.getString(column_index)
            cursor.close()
        } else {
            Toast.makeText(this, "You have not selected and image", Toast.LENGTH_SHORT).show()
        }
    }
}