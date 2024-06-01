package fr.epf.min1.countrysearch.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import fr.epf.min1.countrysearch.R
import fr.epf.min1.countrysearch.data.Country
import fr.epf.min1.countrysearch.localstorage.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FavoriteCountriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_countries)

        val recyclerView =
            findViewById<RecyclerView>(R.id.favorite_countries_recyclerview)

        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

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
}