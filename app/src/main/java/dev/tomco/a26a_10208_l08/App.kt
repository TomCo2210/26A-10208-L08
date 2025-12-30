package dev.tomco.a26a_10208_l08

import android.app.Application
import dev.tomco.a26a_10208_l08.utilities.ImageLoader

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        ImageLoader.init(this)
    }
}