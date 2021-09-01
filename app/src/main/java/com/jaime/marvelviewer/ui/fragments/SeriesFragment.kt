package com.jaime.marvelviewer.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaime.marvelviewer.R
import com.jaime.marvelviewer.databinding.FragmentSeriesBinding
import com.jaime.marvelviewer.db.Series
import com.jaime.marvelviewer.ui.SeriesViewModel
import com.jaime.marvelviewer.ui.groupie.SeriesItem
import com.jaime.marvelviewer.util.ErrorCode
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
        initSwipeRefreshLayout()
        initRecyclerView()
    }

    /**
     * Initialise Observer LiveData from ViewModel
     */
    private fun initObserver() {
        viewModel.seriesData.observe(viewLifecycleOwner) {
            toastMessage(it.errorCode)
            when(it.status) {
                Status.SUCCESS -> {
                    updateData(it.data)
                    binding.progressBarLoadingSeries.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBarLoadingSeries.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.progressBarLoadingSeries.visibility = View.VISIBLE
                }
            }
        }
    }

    /**
     * Update the recyclerview data
     */
    private fun updateData(seriesData: List<Series>?) {
        comicGroupAdapter.apply {
            clear()
            seriesData?.forEach {
                add(SeriesItem(it))
            }
        }

    }

    /**
     * Check if a toast message is available to be displayed
     * @param errorCode the unique error value
     */
    private fun toastMessage(errorCode: ErrorCode?) {
        errorCode?.let {
            val errorMessage = Util.getStringFromErrorCode(resources, errorCode)
            if (errorMessage.isNotEmpty()) {
                Toast.makeText(
                    context,
                    errorMessage,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    /**
     * Initialise RecyclerView Properties
     */
    private fun initRecyclerView() {
        // Set click listener to transition to detail screen
        comicGroupAdapter.setOnItemClickListener { item, _ ->
            // Get comic item unique ID and pass as NavArg, id will be needed for detail API
            val comic = (item as? SeriesItem)?.series
            val comicId = comic?.id ?: 0
            val comicTitle = comic?.title ?: ""
            val thumbnail = comic?.thumbnail ?: ""
            findNavController().navigate(
                SeriesFragmentDirections.actionSeriesFragmentToDetailFragment(
                        comicId,
                        comicTitle,
                        thumbnail
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
     * Initialise Swipe Refresh Properties
     */
    private fun initSwipeRefreshLayout() {
        binding.swipeToRefresh.setOnRefreshListener {
            binding.swipeToRefresh.isRefreshing = false
            viewModel.getComicData()
        }
    }
}