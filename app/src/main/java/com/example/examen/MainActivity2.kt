package com.example.examen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.examen.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperar los datos enviados desde MainActivity
        val nombre = intent.getStringExtra("EXTRA_NOMBRE")
        val noControl = intent.getStringExtra("EXTRA_NO_CONTROL")

        // Mostrar los datos en los TextViews
        binding.displayNombre.text = "Nombre: $nombre"
        binding.displayNumero.text = "No. Control: $noControl"

        binding.btnBack.setOnClickListener {
            val intento2 = Intent(this, MainActivity::class.java)
            startActivity(intento2)
            finish()

        }
    }


}
