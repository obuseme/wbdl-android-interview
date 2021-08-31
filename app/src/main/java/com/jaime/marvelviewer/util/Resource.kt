package com.jaime.marvelviewer.util

data class Resource<out T>(val status: Status, val data: T?, val errorCode: ErrorCode?) {

    companion object {
        fun <T> success(errorCode: ErrorCode? = null, data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, errorCode)
        }

        fun <T> error(errorCode: ErrorCode, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, errorCode)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
    }

}