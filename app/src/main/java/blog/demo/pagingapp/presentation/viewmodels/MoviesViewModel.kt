package blog.demo.pagingapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.*
import blog.demo.pagingapp.domain.entities.Movie
import blog.demo.pagingapp.domain.usecases.MoviesUseCase
import javax.inject.Inject

val moviesPagingConfig = Config(
    pageSize = 10,
    prefetchDistance = 50,
    enablePlaceholders = true
)

class MoviesViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase
) : ViewModel() {
    private lateinit var movies: LiveData<PagedList<Movie>>
    private lateinit var pagingMoviesFactory: PagingMoviesFactory

    fun getMovies(keyword: String): LiveData<PagedList<Movie>>  {
        pagingMoviesFactory = PagingMoviesFactory(moviesUseCase, keyword)
        movies = pagingMoviesFactory.toLiveData(moviesPagingConfig)
        return this.movies
    }

    fun reset() = pagingMoviesFactory.reset()
}
