package com.example.gamerater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gamerater.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.next.setOnClickListener {
            // Verificar si se han aceptado los términos y condiciones
            if (binding.terminos.isChecked) {
                // El checkbox de términos y condiciones está marcado
                if (binding.email.text.isEmpty()) {
                    Toast.makeText(this, "Debe de introducir un email", Toast.LENGTH_SHORT).show()
                } else if (binding.password.text.isEmpty()) {
                    Toast.makeText(this, "Debe de introducir la contraseña", Toast.LENGTH_SHORT).show()
                } else {
                    // Ambos campos están completados y el checkbox está marcado
                    val intent = Intent(this, CreateGameActivity::class.java)
                    startActivity(intent)
                }
            } else {
                // El checkbox de términos y condiciones no está marcado
                Toast.makeText(this, "Debes de aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
            }
        }

        // listener para el checkbox de términos y condiciones
        binding.terminos.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Has aceptado los términos y condiciones", Toast.LENGTH_SHORT).show()
            }

        }
        // Agregar el botón de volver atrás
        binding.back.setOnClickListener {
            finish() // Cierra la actividad actual y vuelve a la anterior
        }
    }
}

