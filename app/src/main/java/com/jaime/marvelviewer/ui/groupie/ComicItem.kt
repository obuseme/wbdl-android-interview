package com.jaime.marvelviewer.ui.groupie

import android.view.View
import com.bumptech.glide.Glide
import com.jaime.marvelviewer.R
import com.jaime.marvelviewer.databinding.ComicItemBinding
import com.jaime.marvelviewer.db.Comic
import com.jaime.marvelviewer.util.Util.toComicYear
import com.jaime.marvelviewer.util.Util.toRating
import com.xwray.groupie.viewbinding.BindableItem

class ComicItem(private val comic: Comic): BindableItem<ComicItemBinding>() {
    override fun getLayout(): Int = R.layout.comic_item

    override fun initializeViewBinding(view: View): ComicItemBinding =
        ComicItemBinding.bind(view)

    override fun bind(viewBinding: ComicItemBinding, position: Int) {
        bindTextViews(viewBinding)

        // Load ImageView with image data from comic object
        Glide.with(viewBinding.root)
            .load(comic.thumbnail)
            .placeholder(R.drawable.marvel_placeholder)
            .error(R.drawable.marvel_placeholder)
            .into(viewBinding.imageViewComicItem)
    }

    /**
     * Bind the TextView text property to the [Comic] data
     * @param viewBinding the reference to the recyclerview item view
     */
    private fun bindTextViews(viewBinding: ComicItemBinding) {
        viewBinding.textViewComicItemTitle.text = comic.title

        val comicsAvailable = comic.available.toString()
        with(viewBinding.textViewComicItemNumber) {
            text = String.format(this.text.toString(), comicsAvailable)
        }

        val startYear = (comic.startYear ?: 0).toString().toComicYear()
        with(viewBinding.textViewComicStartDate) {
            text = String.format(this.text.toString(), startYear)
        }

        val endYear = (comic.endYear ?: 0).toString().toComicYear()
        with(viewBinding.textViewComicEndDate) {
            text = String.format(this.text.toString(), endYear)
        }

        val rating = comic.rating?.toRating()
        with(viewBinding.textViewComicRating) {
            text = String.format(this.text.toString(), rating)
        }
    }
}