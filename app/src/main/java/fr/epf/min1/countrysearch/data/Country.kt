package fr.epf.min1.countrysearch.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Country(
    @PrimaryKey val name : String,
    val continent : String,
    val capital : String,
    val population : Long,
    val flag : String,
    val lat : Double,
    val lng : Double,
    val area: Double,
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
                    45227.0)
            }
    }
}
