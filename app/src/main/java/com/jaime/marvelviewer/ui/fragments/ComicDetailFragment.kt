package com.jaime.marvelviewer.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.jaime.marvelviewer.databinding.FragmentComicDetailBinding
import com.jaime.marvelviewer.ui.MarvelSeriesViewModel
import org.koin.java.KoinJavaComponent.inject

class ComicDetailFragment: BaseFragment<FragmentComicDetailBinding>() {
    private val viewModel: MarvelSeriesViewModel by inject(MarvelSeriesViewModel::class.java)
    private val args: ComicDetailFragmentArgs by navArgs()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentComicDetailBinding
        get() = FragmentComicDetailBinding::inflate

    override fun initOnViewCreated() {
        // TODO: Use with Comic Detail API
        val comicId = args.comicId
    }
}