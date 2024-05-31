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
    val lat : Double,
    val lng : Double,
    val area: Long,
) : Parcelable {
    companion object {
        fun generateCountry(size: Int = 10) =
            (1..size).map{
                Country("name${it}",
                    "continent${it}",
                    "capital${it}",
                    it.toLong(),
                    "https://flagcdn.com/w320/yt.png",
                    59.0,
                    26.0,
                    45227)
            }
    }
}
