package blog.demo.pagingapp.data.repositories

import android.util.Log
import blog.demo.pagingapp.BuildConfig
import blog.demo.pagingapp.core.io.DataResult
import blog.demo.pagingapp.data.services.OmdbApi
import blog.demo.pagingapp.domain.entities.Movie
import blog.demo.pagingapp.domain.entities.MoviesPage
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val omdbService: OmdbApi) {


    suspend fun fetchMovies(searchedText: String, page: Int): DataResult<MoviesPage> {
        return try {
            val response = omdbService.fetchMovies(
                BuildConfig.API_KEY, page.toString(),
                searchedText
            )
            when {
                response.isSuccessful -> {
                    DataResult.Success(
                        MoviesPage(
                            page = page,
                            movies = response.body()?.movies?.map { Movie(it.id, it.title, it.year, it.type, it.pictureUrl) }
                                ?: emptyList(),
                            totalResult = response.body()?.total?.toInt() ?: 0
                        )
                    )
                }
                else -> DataResult.Failure(IOException(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            Log.e("MoviesUseCase", "Failed to fetch data!")
            DataResult.Failure(e)
        }
    }
}
