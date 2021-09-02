package com.jaime.marvelviewer.ui.groupie

import android.view.View
import com.bumptech.glide.Glide
import com.jaime.marvelviewer.R
import com.jaime.marvelviewer.databinding.ComicItemBinding
import com.jaime.marvelviewer.model.comic.Comic
import com.jaime.marvelviewer.util.Util.getPrices
import com.xwray.groupie.viewbinding.BindableItem

class ComicItem(private val comic: Comic): BindableItem<ComicItemBinding>() {
    override fun getLayout(): Int = R.layout.comic_item

    override fun initializeViewBinding(view: View): ComicItemBinding =
            ComicItemBinding.bind(view)

    override fun bind(viewBinding: ComicItemBinding, position: Int) {
        bindTextViews(viewBinding)

        // Load ImageView with image data from comic object
        Glide.with(viewBinding.root)
            .load(comic.thumbnail.getFullImage())
            .placeholder(R.drawable.marvel_placeholder)
            .error(R.drawable.marvel_placeholder)
            .into(viewBinding.imageViewComic)
    }

    /**
     * Ensure item takes up half the row
     * @return half the span of the entire view
     */
    override fun getSpanSize(spanCount: Int, position: Int) = spanCount / 2

    /**
     * Bind the multiple TextView text properties to the [Comic] data class passed in
     * Use Root Resources to get strings from strings.xml
     * @param viewBinding the reference to the recyclerview item view
     */
    private fun bindTextViews(viewBinding: ComicItemBinding) {
        val resources = viewBinding.root.resources

        viewBinding.textViewComicTitle.text = comic.title
        viewBinding.textViewComicIssueNumber.text =
                String.format(resources.getString(R.string.comic_issue_number), comic.issueNumber)
        viewBinding.textViewComicPrice.text =
                String.format(resources.getString(R.string.comic_price), comic.prices?.getPrices())
        viewBinding.textViewComicFormat.text =
                String.format(resources.getString(R.string.comic_format), comic.format)

        viewBinding.textViewComicDescription.text = comic.description
    }
}