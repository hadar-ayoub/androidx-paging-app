package blog.demo.pagingapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blog.demo.pagingapp.R
import blog.demo.pagingapp.core.di.Injectable
import blog.demo.pagingapp.presentation.viewmodels.MoviesViewModel
import javax.inject.Inject

class ListFragment : Fragment(), Injectable {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var moviesListAdapter: MoviesListAdapter

    private val viewModel: MoviesViewModel by viewModels { viewModelFactory }

    private var recyclerView: RecyclerView? = null
    private var editText: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.list_fragment, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        editText = view.findViewById(R.id.keyword_text)

        moviesListAdapter = MoviesListAdapter(this)
        editText?.setOnEditorActionListener {  textView, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    getMovies(textView.text)
                    editText?.clearFocus()
                }
                false
        }
        initRecyclerView()
        return view
    }

    private fun getMovies(text: CharSequence?) {
        viewModel.getMovies(text).observe(viewLifecycleOwner, Observer {
            moviesListAdapter.submitList(it)
        })
    }

    private fun initRecyclerView() {
        val dividerItemDecoration =
            DividerItemDecoration(recyclerView?.context, LinearLayoutManager.VERTICAL)
        recyclerView?.addItemDecoration(dividerItemDecoration)
        recyclerView?.adapter = moviesListAdapter
    }
}