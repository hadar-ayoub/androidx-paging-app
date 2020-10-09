package blog.demo.pagingapp.core.di.coroutines

import javax.inject.Qualifier

@Qualifier
annotation class DefaultDispatcher

@Qualifier
annotation class MainDispatcher

@Qualifier
annotation class IoDispatcher

@Qualifier
annotation class MainImmediateDispatcher
