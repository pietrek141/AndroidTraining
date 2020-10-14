package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [Credentials::class], version = 5, exportSchema = true)
abstract class CredentialsDatabase : RoomDatabase() {

    abstract fun credentialsDao(): CredentialsDao

    companion object {
        private var instance: CredentialsDatabase? = null

        @Synchronized fun getInstance(context: Context): CredentialsDatabase? {
            if (instance == null) {
                instance = databaseBuilder(
                    context,
                    CredentialsDatabase::class.java,
                    "credentials_table"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }

        fun deleteInstanceOfDatabase() {
            instance == null
        }
    }

}