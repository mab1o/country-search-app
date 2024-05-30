package fr.epf.min1.countrysearch

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val name : String,
    val continent : String,
    val capital : String,
    val population : Long,
    val flag : String,
    val map : String
) : Parcelable {
    companion object {
        fun generateCountry(size: Int = 10) =
            (1..size).map{
                Country("name${it}",
                    "continent${it}",
                    "capital${it}",
                    it.toLong(),
                    "https://flagcdn.com/w320/yt.png",
                    "https://goo.gl/maps/1e7MXmfBwQv3TQGF7")
            }
    }
}
