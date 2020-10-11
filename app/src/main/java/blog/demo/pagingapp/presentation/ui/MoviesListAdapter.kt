package blog.demo.pagingapp.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import blog.demo.pagingapp.R
import blog.demo.pagingapp.domain.entities.Movie
import com.bumptech.glide.Glide
import java.lang.StringBuilder

open class MoviesListAdapter(private val fragment: Fragment) :
    PagedListAdapter<Movie, MovieViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            Glide.with(fragment)
                .load(it.pictureUrl)
                .error(R.drawable.ic_image_not_found)
                .into(holder.imageView)

            holder.titleView.text = it.title
            holder.contentView.text = StringBuilder(it.type).append(", ").append(it.year)
            with(holder.itemView) {
                tag = it
            }
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
                    && oldItem.year == newItem.year
                    && oldItem.type == newItem.type
        }
    }
}