package com.jaime.marvelviewer.util

import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

object Util {

    const val BASE_URL = "http://gateway.marvel.com/v1/public"
    const val API_KEY = "57c668bbcdbddcf93f85377d9a635561"
    const val PRIVATE_KEY = "5d338f8e20bdc1b20255fa2328221a5a908c8c4b"

    /**
     * Get current timestamp, used for API param
     */
    private val timeStamp: String = java.lang.String.valueOf(
        TimeUnit.MILLISECONDS.toSeconds(
            System.currentTimeMillis()
        )
    )

    /**
     * Create MD5 Hash param as per Marvel API guidelines
     */
    fun getMD5Hash() = "$timeStamp$PRIVATE_KEY$API_KEY".toMS5Hash()


    /**
     * Extension function to create a MD5 hash
     * https://stackoverflow.com/a/64171625/13110837
     */
    private fun String.toMS5Hash(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
    }
}