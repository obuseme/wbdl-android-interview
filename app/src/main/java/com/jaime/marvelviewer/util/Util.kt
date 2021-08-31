package com.jaime.marvelviewer.util

import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.jaime.marvelviewer.util.Constants.API_KEY
import com.jaime.marvelviewer.util.Constants.PRIVATE_KEY
import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

object Util {

    /**
     * Get current timestamp, used for API param
     */
    val timeStamp: String = java.lang.String.valueOf(
        TimeUnit.MILLISECONDS.toSeconds(
            System.currentTimeMillis()
        )
    )

    /**
     * Create MD5 Hash param as per Marvel API guidelines
     */
    fun getMD5Hash(timeStamp: String) = "$timeStamp$PRIVATE_KEY$API_KEY".toMS5Hash()


    /**
     * Extension function to create a MD5 hash
     * https://stackoverflow.com/a/64171625/13110837
     */
    private fun String.toMS5Hash(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
    }

    /**
     * Specify 'Unknown' if comic year is zero
     */
    fun String.toComicYear(): String {
        return if(this == "0") "Unknown"
        else
            this
    }

    /**
     * Specify '?' if rating is unknown
     */
    fun String.toRating(): String =
        if(isNullOrEmpty()) "?" else this

    /**
     * Set a recyclerview divider
     * @param drawableRes the reference to the specified drawable used as the divider
     */
    fun RecyclerView.setDivider(@DrawableRes drawableRes: Int) {
        val divider = DividerItemDecoration(
            this.context,
            DividerItemDecoration.VERTICAL
        )
        val drawable = ContextCompat.getDrawable(
            this.context,
            drawableRes
        )
        drawable?.let {
            divider.setDrawable(it)
            addItemDecoration(divider)
        }
    }
}