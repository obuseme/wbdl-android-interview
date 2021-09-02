package com.jaime.marvelviewer.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaime.marvelviewer.R
import com.jaime.marvelviewer.databinding.FragmentSeriesBinding
import com.jaime.marvelviewer.ui.SeriesViewModel
import com.jaime.marvelviewer.ui.groupie.SeriesItem
import com.jaime.marvelviewer.util.ErrorCode
import com.jaime.marvelviewer.util.Resource
import com.jaime.marvelviewer.util.Status
import com.jaime.marvelviewer.util.Util
import com.jaime.marvelviewer.util.Util.setDivider
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.java.KoinJavaComponent.inject

class SeriesFragment: BaseFragment<FragmentSeriesBinding>() {
    private val viewModel: SeriesViewModel by inject(SeriesViewModel::class.java)
    private val comicGroupAdapter = GroupAdapter<GroupieViewHolder>()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSeriesBinding
        get() = FragmentSeriesBinding::inflate

    override fun initOnViewCreated() {
        initActionBar(resources.getString(R.string.app_name), false)
        initObserver()
        initSwipeRefresh()
        initRecyclerView()
    }

    /**
     * Initialise the Observer for LiveData from ViewModel
     */
    private fun initObserver() {
        viewModel.seriesData.observe(viewLifecycleOwner) {
            binding.isLoading = (it.status == Status.LOADING)
            when(it.status) {
                Status.SUCCESS -> {
                    updateSeriesItems(it)
                }
                Status.ERROR -> {
                    toastMessage(it.errorCode)
                }
                Status.LOADING -> { /* Covered by data binding */ }
            }
        }
    }

    /**
     * Update the recyclerview with the series items
     * @param seriesItems the response from the ViewModel containing the series items and error code (if any)
     */
    private fun updateSeriesItems(seriesItems: Resource<List<SeriesItem>>) {
        // Check if data comes back with 'using cached data' error code
        toastMessage(seriesItems.errorCode)

        comicGroupAdapter.apply {
            clear()
            seriesItems.data?.let { addAll(it) }
        }
    }

    /**
     * Initialise RecyclerView Properties
     */
    private fun initRecyclerView() {
        // Set click listener to transition to detail screen
        comicGroupAdapter.setOnItemClickListener { item, _ ->
            // Get comic item, id, title and thumbnail are required as nav args
            val comic = (item as? SeriesItem)?.series

            // Navigate to detail screen
            findNavController().navigate(
                SeriesFragmentDirections.actionSeriesFragmentToDetailFragment(
                    comic?.id ?: 0,
                    comic?.title ?: "",
                    comic?.thumbnail ?: ""
                )
            )
        }

        binding.recyclerViewSeriesItems.apply {
            layoutManager = LinearLayoutManager(context)
            setDivider(R.drawable.recycler_view_divider)
            adapter = comicGroupAdapter
        }
    }

    /**
     * Initialise Swipe Refresh Widget
     */
    private fun initSwipeRefresh() {
        binding.swipeToRefreshSeries.apply {
            setOnRefreshListener {
                isRefreshing = false
                viewModel.getSeriesData()
            }
        }
    }
}