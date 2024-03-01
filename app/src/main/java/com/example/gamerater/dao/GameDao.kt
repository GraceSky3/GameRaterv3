package com.example.gamerater.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gamerater.model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun list(): List<Game>

    @Query("DELETE FROM game WHERE id=:id ")
    fun delete(id: Int): Int

    @Query("SELECT * FROM game WHERE review=:review")
    fun listReviewsGames(review:String): List<Game>
    //fun listCategoryGames(category: String): List<Game>

    @Insert
    fun save(game:Game)

    @Update
    fun update(game:Game)

    @Query("SELECT * FROM game where id=:id")
    fun findById (id: Int): Game?
}