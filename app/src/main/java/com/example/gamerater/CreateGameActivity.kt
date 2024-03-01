package com.example.gamerater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.gamerater.database.AppDatabase
import com.example.gamerater.databinding.ActivityCreateGameBinding
import com.example.gamerater.model.Game

class CreateGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateGameBinding

    private lateinit var db: AppDatabase

    private var idGameEditar: Int? = null

    enum class Params{
        ID_GAME;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room
            .databaseBuilder(
                this,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            )
            .allowMainThreadQueries().build()

        idGameEditar = intent.extras?.getInt(Params.ID_GAME.name)

        idGameEditar?.let { idGameEditar ->
            binding.aAdirbutton.text="Modificar"
            db.gameDao().findById(idGameEditar)?.let { game ->
                binding.titulo.setText(game.title)
                binding.categ.setText(game.category)
                binding.plataforma.setText(game.plataform)
                binding.reseA.setText(game.review)
            }

        }
        binding.aAdirbutton.setOnClickListener{
            val titlee = binding.titulo.text.toString()
            val categoryy = binding.categ.text.toString()
            val plataformm = binding.plataforma.text.toString()
            val revieww = binding.reseA.text.toString()

            if(idGameEditar == null) {
                val juego = Game(
                    title = titlee,
                    category = categoryy,
                    plataform = plataformm,
                    review = revieww
                )

                db.gameDao().save(juego)

            }else{
                val game = Game(
                    id = idGameEditar!!,
                    title = titlee,
                    category = categoryy,
                    plataform = plataformm,
                    review = revieww
                )

                db
                    .gameDao()
                    .update(game)

            }


            finish()

            val intent = Intent(
                this,
                MainActivity::class.java
            )


            startActivity(intent)
        }
        // Agregar el botón de volver atrás
        binding.back1.setOnClickListener {
            finish() // Cierra la actividad actual y vuelve a la anterior
        }
    }
}