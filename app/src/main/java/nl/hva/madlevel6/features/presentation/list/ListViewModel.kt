package nl.hva.madlevel6.features.presentation.list

import androidx.lifecycle.MutableLiveData
import nl.hva.madlevel6.core.platform.BaseViewModel
import nl.hva.madlevel6.features.data.models.Movie
import nl.hva.madlevel6.features.domain.usecases.DiscoverMovieUseCase

class ListViewModel(private val discoverMovieUseCase: DiscoverMovieUseCase) : BaseViewModel() {

    val movies = MutableLiveData<List<Movie>>()

    fun getMovies(withOriginalLanguage: String, year: String) {
        run {
            val params = DiscoverMovieUseCase.Params(withOriginalLanguage, year)
            movies.postValue(discoverMovieUseCase(params))
        }
    }

}