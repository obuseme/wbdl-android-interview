package com.jaime.marvelviewer.ui.groupie

import android.view.View
import com.bumptech.glide.Glide
import com.jaime.marvelviewer.R
import com.jaime.marvelviewer.databinding.CharacterItemBinding
import com.jaime.marvelviewer.model.character.Character
import com.xwray.groupie.viewbinding.BindableItem

class CharacterItem(private val character: Character) : BindableItem<CharacterItemBinding>() {
    override fun getLayout(): Int = R.layout.character_item

    override fun initializeViewBinding(view: View): CharacterItemBinding =
        CharacterItemBinding.bind(view)

    override fun bind(viewBinding: CharacterItemBinding, position: Int) {
        bindTextViews(viewBinding)

        // Load ImageView with image data from comic object
        Glide.with(viewBinding.root)
            .load(character.thumbnail.getFullImage())
            .placeholder(R.drawable.marvel_placeholder)
            .error(R.drawable.marvel_placeholder)
            .into(viewBinding.imageViewCharacter)
    }

    /**
     * Ensure item takes up half the row
     * @return half the span of the entire view
     */
    override fun getSpanSize(spanCount: Int, position: Int) = spanCount / 2

    /**
     * Bind the multiple TextView text properties to the [Character] data class passed in
     * Use Root Resources to get strings from strings.xml
     * @param viewBinding the reference to the recyclerview item view
     */
    private fun bindTextViews(viewBinding: CharacterItemBinding) {
        viewBinding.textViewCharacterTitle.text = character.name
        viewBinding.textViewCharacterDescription.text = character.description
    }
}
