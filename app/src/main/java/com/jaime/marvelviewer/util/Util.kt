package com.jaime.marvelviewer.util

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
}