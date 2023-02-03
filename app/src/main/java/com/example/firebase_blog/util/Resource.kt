package com.example.firebase_blog.util

/**
 * Enum class to represent the status of a data operation.
 */
enum class Status {
    SUCCESS, // indicates a successful operation
    ERROR, // indicates a failed operation
    LOADING // indicates that the data is still being loaded
}

/**
 * Data class to represent the result of a data operation.
 *
 * @property status the status of the data operation
 * @property data the data returned from the operation, can be null
 * @property message an optional message providing more information about an error
 */
data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    /**
     * Companion object to provide factory methods for creating instances of Resource
     */
    companion object {
        /**
         * Factory method to create a Resource representing a successful operation.
         *
         * @param data the data returned from the operation, can be null
         * @return a Resource with status set to SUCCESS and data set to the passed in value
         */
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        /**
         * Factory method to create a Resource representing a failed operation.
         *
         * @param msg the error message
         * @return a Resource with status set to ERROR and message set to the passed in value
         */
        fun <T> error(msg: String): Resource<T> {
            return Resource(Status.ERROR, null, msg)
        }

        /**
         * Factory method to create a Resource representing a data operation that is still loading.
         *
         * @return a Resource with status set to LOADING
         */
        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
    }
}