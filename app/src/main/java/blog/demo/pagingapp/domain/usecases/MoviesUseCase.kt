package blog.demo.pagingapp.domain.usecases

import blog.demo.pagingapp.R
import blog.demo.pagingapp.core.di.coroutines.IoDispatcher
import blog.demo.pagingapp.core.io.DataResult
import blog.demo.pagingapp.core.io.Result
import blog.demo.pagingapp.data.repositories.MoviesRepository
import blog.demo.pagingapp.domain.entities.Movie
import blog.demo.pagingapp.domain.entities.MoviesPage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    companion object {
        const val PAGE_SIZE = 10
    }

    suspend fun getInitialMovies(searchedText: String): Result<Pair<Boolean, List<Movie>>> =
        withContext(dispatcher) {
            when (val dataResult = moviesRepository.fetchMovies(searchedText, 1)) {
                is DataResult.Success -> {
                    Result.Success(Pair(isLastPage(dataResult.data), dataResult.data.movies))
                }
                is DataResult.Failure -> {
                    Result.Failure<Pair<Boolean, List<Movie>>>(R.string.default_error)
                }
            }
        }

    suspend fun getMoreMovies(
        searchedText: String,
        page: Int
    ): Result<Pair<Boolean, List<Movie>>> {
        return withContext(dispatcher) {
            when (val dataResult = moviesRepository.fetchMovies(searchedText, page)) {
                is DataResult.Success -> {
                    Result.Success(
                        Pair(
                            isLastPage(dataResult.data) || page == 1,
                            dataResult.data.movies
                        )
                    )
                }
                is DataResult.Failure -> {
                    Result.Failure<Pair<Boolean, List<Movie>>>(R.string.default_error)
                }
            }
        }
    }

    private fun isLastPage(data: MoviesPage): Boolean = PAGE_SIZE * data.page >= data.totalResult
}
