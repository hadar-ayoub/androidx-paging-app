package blog.demo.pagingapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import blog.demo.pagingapp.domain.entities.Movie
import blog.demo.pagingapp.domain.usecases.MoviesUseCase
import javax.inject.Inject

val moviesPagingConfig = Config(
    pageSize = 10,
    prefetchDistance = 30,
    enablePlaceholders = false
)

class MoviesViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase
) : ViewModel() {
    private lateinit var pagingMoviesFactory: PagingMoviesFactory

    fun getMovies(keyword: String) : LiveData<PagedList<Movie>>{
        val pagedKeyedDataSource = PagingDataSource(keyword, viewModelScope, moviesUseCase)
        pagingMoviesFactory = PagingMoviesFactory(pagedKeyedDataSource)
        return pagingMoviesFactory.toLiveData(moviesPagingConfig)
    }

    fun refresh() = pagingMoviesFactory.reset()
}
