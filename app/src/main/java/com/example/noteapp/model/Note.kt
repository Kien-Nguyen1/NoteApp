package com.example.noteapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "note")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id : Int,

    @ColumnInfo
    val title : String,

    @ColumnInfo
    val description : String,

    @ColumnInfo
    val date : String
) : Parcelable