package blog.demo.pagingapp.presentation.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import blog.demo.pagingapp.R

open class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titleView: TextView = view.findViewById(R.id.id_text)
    val imageView: ImageView = view.findViewById(R.id.imageView)
    val contentView: TextView = view.findViewById(R.id.content)
}