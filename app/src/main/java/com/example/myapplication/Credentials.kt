package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "credentials_table")
data class Credentials (@PrimaryKey(autoGenerate = true) var id: Int = 1, var login: String, var password: String){

}
