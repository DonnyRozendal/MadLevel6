package nl.hva.madlevel6.core.navigation

import android.content.Context
import nl.hva.madlevel6.features.data.models.Movie
import nl.hva.madlevel6.features.presentation.detail.DetailActivity

class Navigator {

    fun showDetailActivity(context: Context, movie: Movie) {
        context.startActivity(DetailActivity.getIntent(context, movie))
    }

}