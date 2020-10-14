package com.example.myapplication

import android.app.Application
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

class CredentialsRepo(application: Application) {

    private var credentialsDao: CredentialsDao
    private val executor = SerialExecutor(Executors.newFixedThreadPool(5))

    init {
        CredentialsDatabase.getInstance(application.applicationContext).let {
            credentialsDao = it!!.credentialsDao()
        }
    }

    fun insertCredentials(credentials: Credentials) {
        executor.execute { credentialsDao.insert(credentials) }
    }

    fun updateCredentials(credentials: Credentials) {
        executor.execute { credentialsDao.update(credentials) }
    }

    fun getCredentials(): Future<Credentials?> {
        return Callable { credentialsDao.getCredentials() }.let {
            executor.executeCallable(it)
        }
    }
}