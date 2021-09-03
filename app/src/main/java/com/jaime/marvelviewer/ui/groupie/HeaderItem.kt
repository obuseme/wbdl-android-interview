package com.jaime.marvelviewer.ui.groupie

import android.view.View
import com.jaime.marvelviewer.R
import com.jaime.marvelviewer.databinding.HeaderItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class HeaderItem(private val headerTitle: String) : BindableItem<HeaderItemBinding>() {
    override fun getLayout(): Int = R.layout.header_item

    override fun initializeViewBinding(view: View): HeaderItemBinding =
        HeaderItemBinding.bind(view)

    override fun bind(viewBinding: HeaderItemBinding, position: Int) {
        viewBinding.textviewHeaderItemTitle.text = headerTitle
    }

    /**
     * Ensure Header takes up whole row
     * @return the span of the entire view
     */
    override fun getSpanSize(spanCount: Int, position: Int) = spanCount / 1
}
