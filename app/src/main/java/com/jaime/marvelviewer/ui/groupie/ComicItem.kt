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
     * Bind the multiple TextView text properties to the [Comic] data class passed in
     * Use Root Resources to get strings from strings.xmlk
     * @param viewBinding the reference to the recyclerview item view
     */
    private fun bindTextViews(viewBinding: ComicItemBinding) {
        val resources = viewBinding.root.resources

        viewBinding.textViewComicItemTitle.text = comic.title

        val comicsAvailable = comic.available.toString()
        viewBinding.textViewComicItemNumber.text =
            String.format(resources.getString(R.string.comic_item_number_of_comics), comicsAvailable)

        val startYear = (comic.startYear ?: 0).toString().toComicYear()
        viewBinding.textViewComicStartDate.text =
            String.format(resources.getString(R.string.comic_item_start_date), startYear)

        val endYear = (comic.endYear ?: 0).toString().toComicYear()
        viewBinding.textViewComicEndDate.text =
            String.format(resources.getString(R.string.comic_item_end_date), endYear)

        val rating = comic.rating?.toRating()
        viewBinding.textViewComicRating.text =
            String.format(resources.getString(R.string.comic_item_rated), rating)
    }
}