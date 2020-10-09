package blog.demo.pagingapp.core.io

sealed class Result<out T> {

    data class Success<out T> (val data: T): Result<T>()
    data class Failure<out T>(val msgId: Int): Result<T>()
}
