package fr.epf.min1.countrysearch.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import fr.epf.min1.countrysearch.MainActivity
import fr.epf.min1.countrysearch.R
import fr.epf.min1.countrysearch.data.click
import fr.epf.min1.countrysearch.localstorage.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteCountriesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_countries)

        // set favorite countries on page
        recyclerView =
            findViewById(R.id.favorite_countries_recyclerview)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        tryFetchFavoriteCountries()

        // refresh button
        setRefreshButton()

        // home button
        setHomeButton()
    }

    private fun setRefreshButton() {
        val refreshButton = findViewById<ImageButton>(R.id.favorite_countries_refresh_imagebutton)
        refreshButton.click {
            tryFetchFavoriteCountries()
        }
    }

    private fun tryFetchFavoriteCountries() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "favorite-countries-db"
        ).build()

        lifecycleScope.launch(Dispatchers.IO) {
            val countries = db.countryDao().getAll()
            runOnUiThread {
                recyclerView.adapter = ListCountryAdapter(countries)
            }
        }
    }

    private fun setHomeButton() {
        val homeButton = findViewById<ImageButton>(R.id.favorite_countries_home_imagebutton)
        homeButton.click {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}