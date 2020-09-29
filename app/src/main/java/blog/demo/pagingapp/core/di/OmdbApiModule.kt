package blog.demo.pagingapp.core.di

import blog.demo.pagingapp.data.services.OmdbApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class OmdbApiModule {

    @Provides
    fun providesOmdbApi(): OmdbApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .callFactory(OkHttpClient.Builder().hostnameVerifier { _, _ -> true }.build())
        .baseUrl("http://www.omdbapi.com")
        .build().create(OmdbApi::class.java)
}