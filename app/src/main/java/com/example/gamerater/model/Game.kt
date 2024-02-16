package com.example.gamerater.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class Game(
    @PrimaryKey val title: String,
    @ColumnInfo("category") val category: String,
    @ColumnInfo("plataform") val plataform: String,
    @ColumnInfo("review") val review: String

    )
