package com.caiqueluz.kryptos.network

sealed class NetworkResponse<out TYPE> {

    companion object {
        fun <TYPE> buildNetworkResponse(content: TYPE?): NetworkResponse<TYPE> =
            when (content != null) {
                true -> buildContentNetworkResponse(content)
                false -> buildErrorNetworkResponse()
            }

        fun <FROM, TO> NetworkResponse<FROM>.mapContent(
            transformation: (FROM) -> TO
        ): NetworkResponse<TO> = when (this) {
            is Content -> Content(
                content = transformation.invoke(this.content)
            )

            else -> throw IllegalArgumentException(
                "mapContent only works with NetworkResponse.Content"
            )
        }
    }

    object Loading : NetworkResponse<Nothing>()

    class Content<TYPE>(val content: TYPE) : NetworkResponse<TYPE>()

    class Error<TYPE>(val throwable: Throwable) : NetworkResponse<TYPE>()
}

fun <TYPE> buildContentNetworkResponse(body: TYPE): NetworkResponse<TYPE> =
    NetworkResponse.Content(content = body)

fun <TYPE> buildErrorNetworkResponse(): NetworkResponse<TYPE> =
    NetworkResponse.Error(throwable = Throwable())
