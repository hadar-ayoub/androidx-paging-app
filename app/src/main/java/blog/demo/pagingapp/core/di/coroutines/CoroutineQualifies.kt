package blog.demo.pagingapp.core.di.coroutines

import javax.inject.Qualifier

@Qualifier
annotation class DefaultCoroutineScope

@Qualifier
annotation class MainCoroutineScope

@Qualifier
annotation class IOCoroutineScope

@Qualifier
annotation class MainImmediateCoroutineScope

@Qualifier
annotation class UnconfinedCoroutineScope
