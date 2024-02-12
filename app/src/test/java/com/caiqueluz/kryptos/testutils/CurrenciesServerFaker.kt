package com.caiqueluz.kryptos.testutils

class CurrenciesServerFaker(
    private val responseFaker: ServerResponseFaker
) {

    fun fakeSuccess() {
        responseFaker
            .on(path = "/v1/cryptocurrency/listings/latest?limit=50")
            .answer(code = 200, body = listingSuccessResponseBody())
            .register()

        responseFaker
            .on(path = "/v1/cryptocurrency/info?id=1%2C2%2C3")
            .answer(code = 200, body = imagesSuccessResponseBody())
            .register()
    }

    fun fakeError() {
        responseFaker
            .on(path = "/v1/cryptocurrency/listings/latest?limit=50")
            .answer(code = 404, body = null)
            .register()
    }

    private fun listingSuccessResponseBody() =
        """
                {
                    "data": [
                        {
                            "id": 1,
                            "name": "crypto-1",
                            "symbol": "FKC",
                            "quote": {
                                "USD": {
                                    "price": 123,
                                    "last_updated": "2020-01-01T00:30:00.000Z"
                                }
                            }
                        },
                        {
                            "id": 2,
                            "name": "crypto-2",
                            "symbol": "FKC",
                            "quote": {
                                "USD": {
                                    "price": 123,
                                    "last_updated": "2020-01-01T00:30:00.000Z"
                                }
                            }
                        },
                        {
                            "id": 3,
                            "name": "crypto-3",
                            "symbol": "FKC",
                            "quote": {
                                "USD": {
                                    "price": 123,
                                    "last_updated": "2020-01-01T00:30:00.000Z"
                                }
                            }
                        }
                    ]
                }
            """

    private fun imagesSuccessResponseBody() =
        """
            {
                "data": {
                    "1": {
                        "id": 1,
                        "logo": "http://localhost:8080/fake-image-url-1.png"
                    },
                    "2": {
                        "id": 2,
                        "logo": "http://localhost:8080/fake-image-url-2.png"
                    },
                    "3": {
                        "id": 3,
                        "logo": "http://localhost:8080/fake-image-url-3.png"
                    }
                }
            }
        """
}
