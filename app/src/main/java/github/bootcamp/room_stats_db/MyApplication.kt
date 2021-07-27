package github.bootcamp.room_stats_db

import android.app.Application
import android.content.Context

class MyApplication : Application() {
    private var sApplication: Application? = null

    fun getApplication(): Application{
        return sApplication!!
    }

    fun getContext(): Context{
        return getApplication().applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        sApplication = this
    }

}