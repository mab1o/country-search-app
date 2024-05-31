package fr.epf.min1.countrysearch.api

import fr.epf.min1.countrysearch.data.Country

data class CountryResult(
    val name: Name,
    val capital: List<String>,
    val latlng: List<Double>,
    val area: Double,
    val population: Long,
    val continents: List<String>,
    val flags: Flags
) {
    fun toCountry(): Country {
        return Country(
            name.common,
            continents[0],
            capital[0],
            population,
            flags.png,
            latlng[0],
            latlng[1],
            area
        )
    }
}

data class Name(val common: String)
data class Flags(val png: String)
