package com.example.testsolution.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testsolution.BuildConfig
import com.example.testsolution.app.App
import com.example.testsolution.databinding.FragmentListBinding
import com.example.testsolution.presentation.activity.MainActivity
import com.example.testsolution.presentation.adapters.MoviesAdapter
import com.example.testsolution.presentation.viewmodels.FragmentListViewModel
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentList.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentList : Fragment() {

    lateinit var binding: FragmentListBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: FragmentListViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        (activity as MainActivity).actionBarActivity?.show()
        val adapter = MoviesAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        adapter.addLoadStateListener {
            if(it.refresh is LoadState.Error || it.refresh is LoadState.Loading)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.GONE
        }
        (context?.applicationContext as App).appComponent.inject(this)

        viewModel.moviesLiveData.observe(this, Observer {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        })
        return binding.root
    }

}