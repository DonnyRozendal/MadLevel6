package nl.hva.madlevel6.features.data.repositories

import nl.hva.madlevel6.core.platform.BaseRepository
import nl.hva.madlevel6.core.platform.NetworkHandler
import nl.hva.madlevel6.features.data.datasource.Api
import nl.hva.madlevel6.features.data.models.Movie

class MovieRepository(networkHandler: NetworkHandler, private val api: Api) :
    BaseRepository(networkHandler) {

    fun discoverMovie(withOriginalLanguage: String, year: String): List<Movie> {
        return requestList(
            api.discoverMovie(withOriginalLanguage, year), { it }, emptyList()
        )
    }

}