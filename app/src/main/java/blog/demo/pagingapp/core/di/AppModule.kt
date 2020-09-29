package blog.demo.pagingapp.core.di

import android.app.Application
import android.content.Context
import blog.demo.pagingapp.core.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        ViewModelModule::class
    ]
)
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(app: App): Application = app
}