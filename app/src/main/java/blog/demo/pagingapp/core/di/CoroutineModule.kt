package blog.demo.pagingapp.core.di

import blog.demo.pagingapp.core.di.coroutines.*
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

@Module
class CoroutineModule {

    @Provides
    @MainCoroutineScope
    fun provideMainCoroutineScope(): CoroutineScope = CoroutineScope(Job() + Dispatchers.Main)

    @Provides
    @DefaultCoroutineScope
    fun provideDefaultCoroutineScope(): CoroutineScope = CoroutineScope(Job() + Dispatchers.Default)

    @Provides
    @IOCoroutineScope
    fun provideIOCoroutineScope(): CoroutineScope = CoroutineScope(Job() + Dispatchers.IO)

    @Provides
    @UnconfinedCoroutineScope
    fun provideUnconfinedCoroutineScope(): CoroutineScope =
        CoroutineScope(Job() + Dispatchers.Unconfined)

    @Provides
    @MainImmediateCoroutineScope
    fun provideMainImmediateCoroutineScope(): CoroutineScope =
        CoroutineScope(Job() + Dispatchers.Main.immediate)
}