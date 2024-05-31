package fr.epf.min1.countrysearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.countrysearch.activity.ListCountryAdapter
import fr.epf.min1.countrysearch.api.CountryService
import fr.epf.min1.countrysearch.api.Retrofit
import fr.epf.min1.countrysearch.data.Country
import kotlinx.coroutines.runBlocking

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.main_country_list_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false)
        Log.d(TAG, Country.generateCountry().toString())


        val countryService = Retrofit
            .getInstance()
            .create(CountryService::class.java)

        runBlocking{
            val countries = countryService.getCountryByName("ab")
            Log.d(TAG,"$countries")
            val countriesList = countries.map{
                Country(
                    it.name.common,
                    it.continents[0],
                    it.capital[0],
                    it.population,
                    it.flags.png,
                    it.latlng[0],
                    it.latlng[1],
                    it.area
                )
            }
            recyclerView.adapter = ListCountryAdapter(countriesList)
        }
    }
}
