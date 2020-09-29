package blog.demo.pagingapp.data.services

import blog.demo.pagingapp.data.entities.OmdbSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {
    @GET("/")
    suspend fun fetchMovies(
        @Query("apikey") apikKey: String,
        @Query("page") page: String,
        @Query("s") search: String
    ): Response<OmdbSearch>
}
