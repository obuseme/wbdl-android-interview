package com.jaime.marvelviewer.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.jaime.marvelviewer.R
import com.jaime.marvelviewer.databinding.FragmentComicDetailBinding
import com.jaime.marvelviewer.model.DetailData
import com.jaime.marvelviewer.model.character.Character
import com.jaime.marvelviewer.model.comic.Comic
import com.jaime.marvelviewer.ui.DetailViewModel
import com.jaime.marvelviewer.ui.groupie.CharacterItem
import com.jaime.marvelviewer.ui.groupie.ComicItem
import com.jaime.marvelviewer.ui.groupie.HeaderItem
import com.jaime.marvelviewer.ui.groupie.ImageItem
import com.jaime.marvelviewer.util.Constants.COMIC_DETAIL_SPAN_SIZE
import com.jaime.marvelviewer.util.Status
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import org.koin.java.KoinJavaComponent.inject

class DetailFragment: BaseFragment<FragmentComicDetailBinding>() {
    private val viewModel: DetailViewModel by inject(DetailViewModel::class.java)
    private val args: DetailFragmentArgs by navArgs()

    private val characterGroupAdapter = GroupAdapter<GroupieViewHolder>()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentComicDetailBinding
        get() = FragmentComicDetailBinding::inflate

    override fun initOnViewCreated() {
        initActionBar(args.comicTitle, true)
        initObserver()
        initRecyclerView()

        // Request Series Details
        val comicId = args.comicId.toString()
        viewModel.requestSeriesDetails(comicId)
    }

    /**
     * Initialise Observer LiveData from ViewModel
     */
    private fun initObserver() {
        viewModel.detailData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    updateData(it.data)
                    binding.progressBarLoadingComicDetails.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBarLoadingComicDetails.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.progressBarLoadingComicDetails.visibility = View.VISIBLE
                }
            }
        }
    }

    /**
     * Initialise RecyclerView Properties
     */
    private fun initRecyclerView() {
        characterGroupAdapter.spanCount = COMIC_DETAIL_SPAN_SIZE
        binding.recyclerViewComicDetails.apply {
            layoutManager = GridLayoutManager(context, characterGroupAdapter.spanCount).apply {
                spanSizeLookup = characterGroupAdapter.spanSizeLookup
            }
            adapter = characterGroupAdapter
        }
    }

    /**
     * Update the recyclerview character data
     */
    private fun updateData(detailData: DetailData?) {
        characterGroupAdapter.apply {
            clear()
            add(ImageItem(args.comicSeriesThumbnail))
        }

        val characterData = detailData?.characters?.data
        if(characterData?.isNotEmpty() == true) {
            populateCharacters(characterData)
        }
        else {
            noCharacters()
        }

        val comicData = detailData?.comics?.data
        if(comicData?.isNotEmpty() == true) {
            populateComics(comicData)
        }
        else {
            noComics()
        }
    }

    /**
     * Populate characters in recyclerview
     * @param characterData the list of characters from the API
     */
    private fun populateCharacters(characterData: List<Character>?) {
        characterGroupAdapter.apply {
            add(HeaderItem(resources.getString(R.string.character_detail_header)))

            val section = Section()
            section.apply {
                characterData?.forEach {
                    add(CharacterItem(it))
                }
            }
            add(section)
        }
    }

    /**
     * Inform recyclerview there are no characters therefore display empty message
     */
    private fun noCharacters() {
        characterGroupAdapter.apply {
            add(HeaderItem(resources.getString(R.string.character_detail_no_characters)))
        }
    }

    /**
     * Populate comics in recyclerview
     * @param comicData the list of comics from the API
     */
    private fun populateComics(comicData: List<Comic>?) {
        characterGroupAdapter.apply {
            add(HeaderItem(resources.getString(R.string.comic_detail_header)))

            val section = Section()
            section.apply {
                comicData?.forEach {
                    add(ComicItem(it))
                }
            }
            add(section)
        }
    }

    /**
     * Inform recyclerview there are no comics therefore display empty message
     */
    private fun noComics() {
        characterGroupAdapter.apply {
            add(HeaderItem(resources.getString(R.string.comic_detail_no_characters)))
        }
    }
}