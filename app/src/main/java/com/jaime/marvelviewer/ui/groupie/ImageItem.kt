package com.jaime.marvelviewer.ui.groupie

import android.view.View
import com.bumptech.glide.Glide
import com.jaime.marvelviewer.R
import com.jaime.marvelviewer.databinding.ImageItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class ImageItem(private val thumbnailURL: String) : BindableItem<ImageItemBinding>() {
    override fun getLayout(): Int = R.layout.image_item

    override fun initializeViewBinding(view: View): ImageItemBinding =
        ImageItemBinding.bind(view)

    override fun bind(viewBinding: ImageItemBinding, position: Int) {
        // Load ImageView with image data from comic object
        Glide.with(viewBinding.root)
            .load(thumbnailURL)
            .placeholder(R.drawable.marvel_placeholder)
            .error(R.drawable.marvel_placeholder)
            .into(viewBinding.imageViewItem)
    }

    /**
     * Ensure Image takes up whole row
     * @return the span of the entire view
     */
    override fun getSpanSize(spanCount: Int, position: Int) = spanCount / 1
}
