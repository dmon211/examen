package com.example.examen

// MainActivity.kt
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listaAgenda = ArrayList<Agenda>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarEventos()
        actualizarContadorContactos()
    }

    private fun configurarEventos() {
        binding.btnAgregar.setOnClickListener {
            agregarContacto()
        }

        binding.btnAgenda.setOnClickListener {
            buscarContactoPorNoControl()
            verAgenda()
        }


        //binding.btnBuscar?.setOnClickListener {
         //    buscarContactoPorNoControl()
       // }
    }

    private fun agregarContacto() {
        val nombre = binding.txtNombre.text.toString().trim()
        val noControl = binding.txtNumero.text.toString().trim()

        if (validarCampos(nombre, noControl)) {
            val nuevoContacto = Agenda(nombre, noControl)
            listaAgenda.add(nuevoContacto)

            binding.txtNombre.text.clear()
            binding.txtNumero.text.clear()

            actualizarContadorContactos()

            Toast.makeText(this, "Contacto agregado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validarCampos(nombre: String, noControl: String): Boolean {
        return when {
            nombre.isEmpty() -> {
                binding.txtNombre.error = "El nombre no puede estar vacío"
                binding.txtNombre.requestFocus()
                false
            }

            noControl.isEmpty() -> {
                binding.txtNumero.error = "El no. de control no puede estar vacío"
                binding.txtNumero.requestFocus()
                false
            }

            else -> {
                binding.txtNombre.error = null
                binding.txtNumero.error = null
                true
            }
        }
    }

    private fun verAgenda() {
        if (listaAgenda.isEmpty()) {
            Toast.makeText(this, "No hay contactos en la agenda", Toast.LENGTH_SHORT).show()
            return
        }

        val contactoAMostrar = listaAgenda[0]

        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("EXTRA_NOMBRE", contactoAMostrar.nombre)
        intent.putExtra("EXTRA_NO_CONTROL", contactoAMostrar.numero)

        startActivity(intent)
    }

    private fun buscarContactoPorNoControl() {
        val noControlBuscado = binding.txtContactos.text.toString().trim()

        if (noControlBuscado.isEmpty()) {
            binding.txtContactos.error = "Ingrese un no. de control para buscar"
            binding.txtContactos.requestFocus()
            return
        }

        // Buscar contacto en la lista
        val contactoEncontrado = listaAgenda.find { it.numero == noControlBuscado }

        if (contactoEncontrado != null) {
            // Contacto encontrado
            Toast.makeText(this, "Contacto encontrado: ${contactoEncontrado.nombre}", Toast.LENGTH_LONG).show()

            // Opcional: Mostrar los detalles del contacto encontrado
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("EXTRA_NOMBRE", contactoEncontrado.nombre)
            intent.putExtra("EXTRA_NO_CONTROL", contactoEncontrado.numero)
            startActivity(intent)

        } else {
            // Contacto no encontrado
            Toast.makeText(this, "No se encontró contacto con ese número de control", Toast.LENGTH_SHORT).show()
        }

        // Limpiar campo de búsqueda
        binding.txtContactos.text.clear()
        binding.txtContactos.error = null
    }

    private fun actualizarContadorContactos() {
        binding.displayContactos.text = "Contactos: ${listaAgenda.size}"
    }
}