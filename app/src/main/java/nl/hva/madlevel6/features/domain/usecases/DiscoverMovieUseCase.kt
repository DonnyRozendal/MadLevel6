package nl.hva.madlevel6.features.domain.usecases

import nl.hva.madlevel6.core.interactor.UseCase
import nl.hva.madlevel6.features.data.models.Movie
import nl.hva.madlevel6.features.data.repositories.MovieRepository

class DiscoverMovieUseCase(private val movieRepository: MovieRepository) :
    UseCase<List<Movie>, DiscoverMovieUseCase.Params>() {

    override fun invoke(params: Params): List<Movie> {
        return movieRepository.discoverMovie(params.withOriginalLanguage, params.year)
    }

    data class Params(
        val withOriginalLanguage: String,
        val year: String
    )

}