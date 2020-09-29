package blog.demo.pagingapp.core.io

sealed class DataResult<out T> {
    data class Success<out T>(val data: T) : DataResult<T>()
    data class Failure<out T>(val throwable: Throwable) : DataResult<T>()
}
