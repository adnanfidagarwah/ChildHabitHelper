package com.example.dummyproject.ui.list.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("category")
    val category: String? = "", // women's clothing

    @SerializedName("description")
    val description: String? =
        "", // 95%Cotton,5%Spandex, Features: Casual, Short Sleeve, Letter Print,V-Neck,Fashion Tees, The fabric is soft and has some stretch., Occasion: Casual/Office/Beach/School/Home/Street. Season: Spring,Summer,Autumn,Winter.

    @SerializedName("id")
    val id: Int? = 0, // 20

    @SerializedName("image")
    var image: String? = "", // https://fakestoreapi.com/img/61pHAEJ4NML._AC_UX679_.jpg

    @SerializedName("price")
    var price: Double? = 0.0, // 109.95

    @SerializedName("rating")
    val rating: Rating? = Rating(),

    @SerializedName("title")
    var title: String? = "" // DANVOUY Womens T Shirt Casual Cotton Short {
) {

    @Keep
    data class Rating(
        @SerializedName("count")
        val count: Int? = 0, // 145
        @SerializedName("rate")
        var rate: Double? = 0.0 // 3.6
    )
}
