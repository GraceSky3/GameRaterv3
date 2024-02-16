package com.example.gamerater

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.gamerater.database.AppDatabase
import com.example.gamerater.databinding.ActivityMainBinding
import com.example.gamerater.model.Game


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        db = Room
            .databaseBuilder(
                this,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            )
            .allowMainThreadQueries().build()

        binding.gamesRecycler.layoutManager =
            GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)

        binding.gamesRecycler.adapter = GameAdapter(
            db.gameDao().list(), this, db
        )

        binding.addbutton.setOnClickListener {
            val createGameIntent = Intent(
                this, CreateGameActivity::class.java
            )

            startActivity(createGameIntent)
        }



    }

    fun createInitialData() {
        db.gameDao().save(
            Game("Until Dawn", "decision", "ps4", "good")
        )

        db.gameDao().save(
            Game("Far Cry", "aventura", "ps4", "bugs")

        )
        db.gameDao().save(
            Game("Elden Ring", "simulacion", "pc", "goty")
        )


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.añadirjuegosItem-> {
                val intent = Intent(
                    this, CreateGameActivity::class.java
                )
                startActivity(intent)
            }
            R.id.loginItem->{
                val intent = Intent(
                    this, InicioActivity::class.java
                )
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onResume() {
        super.onResume()

        val adapter = binding.gamesRecycler.adapter as GameAdapter
        adapter.games = db.gameDao().list()
        adapter.notifyDataSetChanged()
    }
}
