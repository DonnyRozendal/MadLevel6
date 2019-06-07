package nl.hva.madlevel6.features.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import nl.hva.madlevel6.R
import nl.hva.madlevel6.features.data.models.Movie


class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "MOVIE"

        fun getIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE, movie)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = ""
        }

        initViews()
    }

    private fun initViews() {
        val movie = intent.getParcelableExtra<Movie>(MOVIE)
        movie.apply {
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w1280$backdropPath")
                .into(imageViewBackdrop)
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w500$posterPath")
                .into(imageViewPoster)
            textViewTitle.text = title
            textViewReleaseDate.text = getString(R.string.detail_release_date, releaseDate)
            textViewRating.text = voteAverage.toString()
            textViewOverviewBody.text = overview
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

}