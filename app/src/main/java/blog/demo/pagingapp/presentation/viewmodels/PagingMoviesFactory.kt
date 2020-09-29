package blog.demo.pagingapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import blog.demo.pagingapp.domain.entities.Movie
import blog.demo.pagingapp.domain.usecases.MoviesUseCase


class PagingMoviesFactory constructor(
    private val moviesUseCase: MoviesUseCase,
    private val searchedText: String
) : DataSource.Factory<Int, Movie>() {
    private val sourceLiveData = MutableLiveData<PageKeyedDataSource<Int, Movie>>()

    override fun create(): DataSource<Int, Movie> {
        val pagingUseCase = moviesUseCase.build(searchedText)
        sourceLiveData.postValue(pagingUseCase)
        return pagingUseCase
    }

    fun reset() {
        sourceLiveData.value?.invalidate()
    }
}