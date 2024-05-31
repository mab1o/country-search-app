package fr.epf.min1.countrysearch.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {
    @GET("name/{name}")
    suspend fun getCountryByName(@Path("name") name: String): Response<List<CountryResult>>
}