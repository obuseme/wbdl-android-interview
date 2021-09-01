package com.jaime.marvelviewer.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.jaime.marvelviewer.R
import com.jaime.marvelviewer.databinding.FragmentComicDetailBinding
import com.jaime.marvelviewer.model.character.Character
import com.jaime.marvelviewer.ui.CharacterViewModel
import com.jaime.marvelviewer.ui.groupie.CharacterItem
import com.jaime.marvelviewer.ui.groupie.HeaderItem
import com.jaime.marvelviewer.util.Constants.COMIC_DETAIL_SPAN_SIZE
import com.jaime.marvelviewer.util.Status
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import org.koin.java.KoinJavaComponent.inject

class ComicDetailFragment: BaseFragment<FragmentComicDetailBinding>() {
    private val viewModel: CharacterViewModel by inject(CharacterViewModel::class.java)
    private val args: ComicDetailFragmentArgs by navArgs()

    private val characterGroupAdapter = GroupAdapter<GroupieViewHolder>()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentComicDetailBinding
        get() = FragmentComicDetailBinding::inflate

    override fun initOnViewCreated() {
        initActionBar(args.comicTitle, true)
        initObserver()
        initRecyclerView()

        // Request Character
        val comicId = args.comicId.toString()
        viewModel.getCharacterData(comicId)
    }

    /**
     * Initialise Observer LiveData from ViewModel
     */
    private fun initObserver() {
        viewModel.characterData.observe(viewLifecycleOwner) {
            when(it.status) {
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
     * Update the recyclerview character data
     */
    private fun updateData(characterData: List<Character>?) {
        characterGroupAdapter.apply {
            clear()
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
}