package blog.demo.pagingapp.domain.usecases

import androidx.paging.PageKeyedDataSource
import blog.demo.pagingapp.core.di.coroutines.IOCoroutineScope
import blog.demo.pagingapp.core.io.DataResult
import blog.demo.pagingapp.data.repositories.MoviesRepository
import blog.demo.pagingapp.domain.entities.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
    @IOCoroutineScope private val coroutineScope: CoroutineScope,
    private val moviesRepository: MoviesRepository
) {
    companion object{
        const val PAGE_SIZE = 10
    }
    fun build(searchedText: String): PageKeyedDataSource<Int, Movie> =
        object : PageKeyedDataSource<Int, Movie>() {
            override fun loadInitial(
                params: LoadInitialParams<Int>,
                callback: LoadInitialCallback<Int, Movie>
            ) {
                getInitialMovies(searchedText, callback)
            }

            override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
                getMoreMovies(searchedText, params.key, params.key + 1, callback)
            }

            override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
                getMoreMovies(searchedText, params.key, params.key - 1, callback)
            }
        }

    private fun getInitialMovies(
        searchedText: String,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, Movie>
    ) {
        coroutineScope.launch {
            when (val result = moviesRepository.fetchMovies(searchedText, 1)) {
                is DataResult.Success -> {
                    callback.onResult(result.data.movies, null, 2)
                }
                is DataResult.Failure -> {
                }
            }
        }
    }

    private fun getMoreMovies(
        searchedText: String,
        page: Int,
        adjacentPage: Int,
        callback: PageKeyedDataSource.LoadCallback<Int, Movie>
    ) {
        coroutineScope.launch {
            when (val result = moviesRepository.fetchMovies(searchedText, page)) {
                is DataResult.Success -> {
                    val lastFetch = PAGE_SIZE * result.data.page >= result.data.totalResult
                    callback.onResult(result.data.movies, if(!lastFetch) adjacentPage else null)
                }
                is DataResult.Failure -> {
                }
            }
        }
    }
}
