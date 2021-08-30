package com.jaime.marvelviewer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaime.marvelviewer.R
import com.jaime.marvelviewer.databinding.FragmentComicBinding
import com.jaime.marvelviewer.model.ComicData
import com.jaime.marvelviewer.ui.groupie.ComicItem
import com.jaime.marvelviewer.util.Status
import com.jaime.marvelviewer.util.Util.setDivider
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import kotlinx.coroutines.flow.collect

class ComicFragment: Fragment() {
    private var _binding: FragmentComicBinding? = null
    private val binding get() = _binding!!

    val viewModel: MarvelSeriesViewModel by inject(MarvelSeriesViewModel::class.java)

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

        lifecycleScope.launch {
            viewModel.comicData.collect {
                when(it.status) {
                    Status.SUCCESS -> {
                        initRecyclerView(it.data)
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Initialise recyclerview with groupie bindings
     */
    private fun initRecyclerView(comicData: ComicData?) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>()

        comicData?.results?.forEach {
            groupAdapter.add(
                ComicItem(it)
            )
        }

        binding.recyclerViewComicItems.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(context)
            setDivider(R.drawable.recycler_view_divider)
        }
    }
}