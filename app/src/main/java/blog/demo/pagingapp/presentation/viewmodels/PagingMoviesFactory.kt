package blog.demo.pagingapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import blog.demo.pagingapp.domain.entities.Movie
import blog.demo.pagingapp.domain.usecases.MoviesUseCase


class PagingMoviesFactory constructor(
    private val pageKeyedDataSource: PageKeyedDataSource<Int, Movie>
) : DataSource.Factory<Int, Movie>() {
    private val sourceLiveData = MutableLiveData<PageKeyedDataSource<Int, Movie>>()

    override fun create(): DataSource<Int, Movie> {
        sourceLiveData.postValue(pageKeyedDataSource)
        return pageKeyedDataSource
    }
}
