package com.jaime.marvelviewer.util

import android.content.res.Resources
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.jaime.marvelviewer.R
import com.jaime.marvelviewer.util.Constants.API_KEY
import com.jaime.marvelviewer.util.Constants.PRIVATE_KEY
import java.lang.Exception
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

    /**
     * Get a human readable string from Android resources (strings.xml)
     * @param resources the reference to Android resources
     * @param errorCode the error code to be converted back into its enum form [ErrorCode]
     * @return the string error message
     */
    fun getStringFromErrorCode(resources: Resources, errorCode: String?): String {
        return try {
            val error = ErrorCode.valueOf(errorCode ?: "")
            return when (error) {
                ErrorCode.DB_EMPTY_OR_NULL -> {
                    resources.getString(R.string.error_db_null_or_empty)
                }
                ErrorCode.USING_CACHED_DATA -> {
                    resources.getString(R.string.error_using_cached_data)
                }
            }
        }
        catch (e: Exception) { "" }
    }
}