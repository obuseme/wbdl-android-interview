package com.jaime.marvelviewer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaime.marvelviewer.R
import com.jaime.marvelviewer.databinding.FragmentComicBinding
import com.jaime.marvelviewer.db.Comic
import com.jaime.marvelviewer.ui.groupie.ComicItem
import com.jaime.marvelviewer.util.ErrorCode
import com.jaime.marvelviewer.util.Status
import com.jaime.marvelviewer.util.Util
import com.jaime.marvelviewer.util.Util.setDivider
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.java.KoinJavaComponent.inject


class ComicFragment: Fragment() {
    private var _binding: FragmentComicBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MarvelSeriesViewModel by inject(MarvelSeriesViewModel::class.java)
    private val comicGroupAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentComicBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initSwipeRefreshLayout()
        initRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Initialise Observer LiveData from ViewModel
     */
    private fun initObserver() {
        viewModel.comicData.observe(viewLifecycleOwner) {
            toastMessage(it.errorCode)
            when(it.status) {
                Status.SUCCESS -> {
                    updateData(it.data)
                    binding.progressBarLoadingComics.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBarLoadingComics.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.progressBarLoadingComics.visibility = View.VISIBLE
                }
            }
        }
    }

    /**
     * Update the recyclerview data
     */
    private fun updateData(comicData: List<Comic>?) {
        comicGroupAdapter.clear()
        comicData?.forEach {
            comicGroupAdapter.add(
                ComicItem(it)
            )
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
        binding.recyclerViewComicItems.apply {
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