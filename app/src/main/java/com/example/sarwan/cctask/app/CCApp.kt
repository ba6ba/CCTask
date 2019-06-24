package com.example.sarwan.cctask.app

import android.app.Application
import com.example.sarwan.cctask.repostiory.CCTaskRepository

class CCApp : Application() {

    private lateinit var repository: CCTaskRepository

    override fun onCreate() {
        super.onCreate()
        repository = CCTaskRepository(this)
    }

    fun getRepository() = repository
}