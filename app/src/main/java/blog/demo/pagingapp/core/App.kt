package blog.demo.pagingapp.core

import android.app.Activity
import android.app.Application
import blog.demo.pagingapp.core.di.AppInjector
import blog.demo.pagingapp.core.di.DaggerAppComponent
import dagger.android.*
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject lateinit var androidInjector : DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }
}
