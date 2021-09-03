package com.jaime.marvelviewer.ui.groupie

import android.view.View
import com.bumptech.glide.Glide
import com.jaime.marvelviewer.R
import com.jaime.marvelviewer.databinding.SeriesItemBinding
import com.jaime.marvelviewer.db.Series
import com.jaime.marvelviewer.util.Util.toComicYear
import com.jaime.marvelviewer.util.Util.toRating
import com.xwray.groupie.viewbinding.BindableItem

class SeriesItem(val series: Series) : BindableItem<SeriesItemBinding>() {
    override fun getLayout(): Int = R.layout.series_item

    override fun initializeViewBinding(view: View): SeriesItemBinding =
        SeriesItemBinding.bind(view)

    override fun bind(viewBinding: SeriesItemBinding, position: Int) {
        bindTextViews(viewBinding)

        // Load ImageView with image data from comic object
        Glide.with(viewBinding.root)
            .load(series.thumbnail)
            .placeholder(R.drawable.marvel_placeholder)
            .error(R.drawable.marvel_placeholder)
            .into(viewBinding.imageViewComicItem)
    }

    /**
     * Bind the multiple TextView text properties to the [Series] data class passed in
     * Use Root Resources to get strings from strings.xml
     * @param viewBinding the reference to the recyclerview item view
     */
    private fun bindTextViews(viewBinding: SeriesItemBinding) {
        val resources = viewBinding.root.resources

        viewBinding.textViewComicItemTitle.text = series.title

        val comicsAvailable = series.available.toString()
        viewBinding.textViewComicItemNumber.text =
            String.format(
                resources.getString(R.string.series_item_number_of_comics),
                comicsAvailable
            )

        val startYear = (series.startYear ?: 0).toString().toComicYear()
        viewBinding.textViewComicStartDate.text =
            String.format(resources.getString(R.string.series_item_start_date), startYear)

        val endYear = (series.endYear ?: 0).toString().toComicYear()
        viewBinding.textViewComicEndDate.text =
            String.format(resources.getString(R.string.series_item_end_date), endYear)

        val rating = series.rating?.toRating()
        viewBinding.textViewComicRating.text =
            String.format(resources.getString(R.string.series_item_rated), rating)
    }
}
