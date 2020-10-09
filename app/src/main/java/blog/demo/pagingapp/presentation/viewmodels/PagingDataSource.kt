package blog.demo.pagingapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.PageKeyedDataSource
import blog.demo.pagingapp.core.io.Result
import blog.demo.pagingapp.domain.entities.Movie
import blog.demo.pagingapp.domain.usecases.MoviesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PagingDataSource(
    private val keyword: String,
    private val viewModelScope: CoroutineScope,
    private val moviesUseCase: MoviesUseCase
) : PageKeyedDataSource<Int, Movie>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        viewModelScope.launch {
            when (val result = moviesUseCase.getInitialMovies(keyword)) {
                is Result.Success<Pair<Boolean, List<Movie>>> -> {
                    val movies = result.data.second
                    val isLastPage = result.data.first
                    callback.onResult(movies, null, if (!isLastPage) 2 else null)
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        viewModelScope.launch {
            when (val result = moviesUseCase.getMoreMovies(keyword, params.key)) {
                is Result.Success<Pair<Boolean, List<Movie>>> -> {
                    val movies = result.data.second
                    val isLastPage = result.data.first
                    callback.onResult(
                        movies,
                        if (!isLastPage) params.key + 1 else null
                    )
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        viewModelScope.launch {
            when (val result = moviesUseCase.getMoreMovies(keyword, params.key)) {
                is Result.Success<Pair<Boolean, List<Movie>>> -> {
                    val movies = result.data.second
                    val isLastPage = result.data.first
                    callback.onResult(
                        movies,
                        if (!isLastPage) params.key - 1 else null
                    )
                }
            }
        }
    }
}