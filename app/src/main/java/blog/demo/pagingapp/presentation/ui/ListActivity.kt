package blog.demo.pagingapp.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blog.demo.pagingapp.R
import blog.demo.pagingapp.presentation.viewmodels.MoviesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.AndroidInjection
import javax.inject.Inject


class ListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MoviesViewModel by viewModels { viewModelFactory }

    private lateinit var moviesListAdapter: MoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_item_list)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title
        moviesListAdapter = MoviesListAdapter(this)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            viewModel.getMovies("super").observe(this, Observer {
                moviesListAdapter.submitList(it)
            })
/*
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
*/
        }

        findViewById<RecyclerView>(R.id.item_list)?.let {
            val dividerItemDecoration = DividerItemDecoration(
                it.context, LinearLayoutManager.VERTICAL
            )
            it.addItemDecoration(dividerItemDecoration)
            it.adapter = moviesListAdapter
        }
    }
}
