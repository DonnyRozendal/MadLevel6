package nl.hva.madlevel6.features.presentation.list

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import nl.hva.madlevel6.R
import nl.hva.madlevel6.core.extension.failure
import nl.hva.madlevel6.core.extension.observe
import nl.hva.madlevel6.core.navigation.Navigator
import nl.hva.madlevel6.features.data.models.Movie
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    private val viewModel by viewModel<ListViewModel>()
    private val navigator by inject<Navigator>()

    private val adapter = ListAdapter {
        navigator.showDetailActivity(this, it)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        supportActionBar?.setTitle(R.string.list_title)

        viewModel.apply {
            observe(movies, ::handleMovies)
            failure(failure, ::handleFailure)
        }
        initViews()
    }

    private fun initViews() {
        buttonQuery.setOnClickListener {
            viewModel.getMovies("en", textInputEditTextQuery.text.toString())
            hideKeyboard()
        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.layoutManager = GridLayoutManager(this, 2)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 4)
        }
        recyclerView.adapter = adapter
    }

    private fun handleMovies(movies: List<Movie>) {
        adapter.submitList(movies)
    }

    private fun handleFailure(throwable: Throwable) {

    }

    private fun hideKeyboard() {
        currentFocus?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

}