package fr.epf.min1.countrysearch.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min1.countrysearch.R
import fr.epf.min1.countrysearch.data.Country

class FavoriteCountriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_countries)

        val recyclerView =
            findViewById<RecyclerView>(R.id.favorite_countries_recyclerview)

        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerView.adapter = ListCountryAdapter(Country.generateCountry())
    }
}