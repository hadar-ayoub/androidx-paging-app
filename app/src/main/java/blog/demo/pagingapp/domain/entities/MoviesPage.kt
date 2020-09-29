package blog.demo.pagingapp.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class MoviesPage(
    val page: Int,
    val movies: List<Movie>,
    val totalResult: Int
)

@Parcelize
data class Movie(val id: String, val title: String, val year: String, val type: String, val pictureUrl: String) : Parcelable