package com.example.gamerater

import android.content.ClipData.Item
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gamerater.database.AppDatabase
import com.example.gamerater.databinding.GameLayoutBinding
import com.example.gamerater.model.Game
import java.text.FieldPosition

class GameAdapter(
    var games: List<Game>,
    var context: Context,
    var db: AppDatabase
) :

    Adapter<GameAdapter.ItemViewHolder>(){

    private val layoutInflater = LayoutInflater.from(context)

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            layoutInflater.inflate(R.layout.game_layout, null)
        )
    }


    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int){
        val game = games[position]
        val binding = GameLayoutBinding.bind(holder.itemView)

        binding.titletextView.text = game.title
        binding.categtextView.text = game.category
        binding.plataformatextView.text = game.plataform
        binding.reseAtextView.text = game.review


        // eliminar
        binding.deleteImg.setOnClickListener{
            val deletedRows = db.gameDao().delete(game.id)

            games = db.gameDao().list()

            notifyDataSetChanged()
            if(deletedRows == 0){
                Toast.makeText(context, "No se ha eliminado ningún libro", Toast.LENGTH_LONG).show()
            }
        }
        //edit
        binding.editimag.setOnClickListener {
        val editGameIntent = Intent(context, CreateGameActivity::class.java)
        editGameIntent.putExtra(CreateGameActivity.Params.ID_GAME.name, game.id)

         context.startActivity(editGameIntent)

        }

    }

    }


