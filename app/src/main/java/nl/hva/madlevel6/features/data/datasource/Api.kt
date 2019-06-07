package nl.hva.madlevel6.features.data.datasource

import nl.hva.madlevel6.features.data.models.BaseResponse
import nl.hva.madlevel6.features.data.models.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("discover/movie")
    fun discoverMovie(
        @Query("with_original_language") withOriginalLanguage: String,
        @Query("year") year: String
    ): Call<BaseResponse<List<Movie>>>

}