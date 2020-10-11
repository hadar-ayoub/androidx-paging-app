package blog.demo.pagingapp.core.di

import blog.demo.pagingapp.presentation.ui.ListFragment
import blog.demo.pagingapp.presentation.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ListFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeListActivity(): ListFragment
}
