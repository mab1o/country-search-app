package fr.epf.min1.countrysearch.api

data class CountryResult(
    val name : Name,
    val capital : List<String>,
    val latlng : List<Double>,
    val area : Double,
    val population : Long,
    val continents : List<String>,
    val flags : Flags
)
data class Name(val common : String)
data class Flags(val png : String)
