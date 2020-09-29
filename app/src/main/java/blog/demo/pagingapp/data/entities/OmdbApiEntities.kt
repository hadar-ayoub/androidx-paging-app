package blog.demo.pagingapp.data.entities

import com.google.gson.annotations.SerializedName
import java.util.*

data class OmdbSearch (
    @SerializedName("Search") val movies : List<OmdbMovie>,
    @SerializedName("totalResults") val total : String,
    @SerializedName("Response") val response : String
)

data class OmdbMovie(
    @SerializedName("imdbID") val id: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Poster") val pictureUrl: String
)
