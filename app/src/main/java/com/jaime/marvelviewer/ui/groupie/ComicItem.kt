package com.jaime.marvelviewer.ui.groupie

import android.view.View
import com.bumptech.glide.Glide
import com.jaime.marvelviewer.R
import com.jaime.marvelviewer.databinding.ComicItemBinding
import com.jaime.marvelviewer.model.Comic
import com.jaime.marvelviewer.util.Util.toComicYear
import com.xwray.groupie.viewbinding.BindableItem

class ComicItem(private val comic: Comic): BindableItem<ComicItemBinding>() {
    override fun getLayout(): Int = R.layout.comic_item

    override fun initializeViewBinding(view: View): ComicItemBinding =
        ComicItemBinding.bind(view)

    override fun bind(viewBinding: ComicItemBinding, position: Int) {
        viewBinding.textViewComicItemTitle.text = comic.title
        viewBinding.textViewComicItemNumber.text = comic.comics.available.toString()
        viewBinding.textViewComicStartDate.text = (comic.startYear ?: 0).toString().toComicYear()
        viewBinding.textViewComicEndDate.text = (comic.endYear ?: 0).toString().toComicYear()

        Glide.with(viewBinding.root)
            .load(comic.thumbnail.getFullImage())
            .placeholder(R.drawable.marvel_placeholder)
            .error(R.drawable.marvel_placeholder)
            .into(viewBinding.imageViewComicItem)
    }
}