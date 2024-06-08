package fr.epf.min1.countrysearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.countrysearch.activity.FavoriteCountriesActivity
import fr.epf.min1.countrysearch.activity.ListCountryAdapter
import fr.epf.min1.countrysearch.api.CountryService
import fr.epf.min1.countrysearch.api.Retrofit
import fr.epf.min1.countrysearch.data.Country
import kotlinx.coroutines.runBlocking

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init recyclerView
        recyclerView = findViewById(R.id.main_country_list_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        // find country by Name
        setSearchButtonOnclick()

        // set button to go to favorite page
        setFavoriteButtonOnClick()
    }

    private fun setFavoriteButtonOnClick() {
        val favoriteButton = findViewById<ImageButton>(R.id.main_favorite_imagebutton)
        favoriteButton.setOnClickListener {
            val intent = Intent(
                this,
                FavoriteCountriesActivity::class.java
            )
            startActivity(intent)
        }
    }

    private fun setSearchButtonOnclick() {
        val searchButton = findViewById<Button>(R.id.main_search_button)
        val searchNameEditText = findViewById<EditText>(R.id.main_country_search_edittext)

        searchButton.setOnClickListener {
            val searchName = searchNameEditText.text.toString()

            if (searchName.isNotEmpty()) {
                findCountryByName(searchName)
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Search bar can't be empty: Try again!", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun findCountryByName(searchName: String) {
        val countryService = Retrofit
            .getInstance()
            .create(CountryService::class.java)

        runBlocking {
            val countriesList = tryToFetchCountry(countryService, searchName)
            if (countriesList.isNotEmpty()) {
                recyclerView.adapter = ListCountryAdapter(countriesList)
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "An error occurred: Try again!", Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private suspend fun tryToFetchCountry(countryService: CountryService, searchName: String)
            : List<Country> {
        val maxRetries = 5
        var currentRetry = 0
        var success = false
        var countriesList: List<Country> = emptyList()

        while (currentRetry < maxRetries && !success) {
            Log.d(TAG, "Reaching API: try n°${currentRetry + 1} of $maxRetries")
            try {
                Log.d(TAG, searchName)
                val response = countryService.getCountryByName(searchName)
                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "${response.body()!!}")
                    countriesList = response.body()!!.map { it.toCountry() }
                    success = true
                }
            } catch (e: Exception) {
                Log.d(TAG, e.stackTraceToString())
            }
            currentRetry++
        }
        return countriesList
    }
}
