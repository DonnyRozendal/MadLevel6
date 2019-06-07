package nl.hva.madlevel6.features.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_item_cell.view.*
import nl.hva.madlevel6.R
import nl.hva.madlevel6.features.data.models.Movie

class ListAdapter(private val clickListener: (Movie) -> Unit) :
    ListAdapter<Movie, nl.hva.madlevel6.features.presentation.list.ListAdapter.ViewHolder>(
        MovieDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_item_cell,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie, clickListener: (Movie) -> Unit) = with(view) {
            textViewNumber.text = (adapterPosition + 1).toString()
            Glide.with(view)
                .load("https://image.tmdb.org/t/p/w500" + movie.posterPath)
                .into(imageViewPoster)

            setOnClickListener { clickListener(movie) }
        }

    }

    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
    }

}