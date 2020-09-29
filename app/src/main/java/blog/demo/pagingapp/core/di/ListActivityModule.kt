package blog.demo.pagingapp.core.di

import blog.demo.pagingapp.presentation.ui.ListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ListActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeListActivity(): ListActivity
}