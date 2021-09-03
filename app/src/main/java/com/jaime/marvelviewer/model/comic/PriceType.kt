package com.jaime.marvelviewer.model.comic

enum class PriceType {
    printPrice {
        override val friendlyString: String
            get() = "(Print)"
    },
    digitalPurchasePrice {
        override val friendlyString: String
            get() = "(Digital)"
    };

    abstract val friendlyString: String
}
