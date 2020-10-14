package com.example.myapplication

import androidx.room.*

@Dao
interface CredentialsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (credentials: Credentials)

    @Update
    fun update (credentials: Credentials)

    @Query("SELECT * FROM credentials_table")
    fun getCredentials() : Credentials?
}