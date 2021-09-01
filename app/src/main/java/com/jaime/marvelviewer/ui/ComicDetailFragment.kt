package com.jaime.marvelviewer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jaime.marvelviewer.databinding.FragmentComicDetailBinding
import org.koin.java.KoinJavaComponent.inject

class ComicDetailFragment: Fragment() {
    private var _binding: FragmentComicDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MarvelSeriesViewModel by inject(MarvelSeriesViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentComicDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}