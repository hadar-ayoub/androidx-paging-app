package blog.demo.pagingapp.core.di

import blog.demo.pagingapp.core.App
import blog.demo.pagingapp.presentation.ui.ListActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        CoroutineModule::class,
        OmdbApiModule::class,
        ListActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder
        fun build(): AppComponent
    }
}