package fr.epf.min1.countrysearch.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.bumptech.glide.Glide
import fr.epf.min1.countrysearch.data.Country
import fr.epf.min1.countrysearch.R
import fr.epf.min1.countrysearch.localstorage.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

private const val TAG = "DetailsCountryActivity"

class DetailsCountryActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load osmdroid configuration
        Configuration.getInstance()
            .load(applicationContext, getSharedPreferences("osmdroid", MODE_PRIVATE));

        setContentView(R.layout.activity_details_country)

        // set info on screen
        setCountryInfoAndFlag()

        // put country in favorite
        setButtonToAddInFavorite()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setCountryInfoAndFlag() {
        val countryTextView = findViewById<TextView>(R.id.details_country_country_textview)
        val capitalTextView = findViewById<TextView>(R.id.details_country_capital_textview)
        val continentTextView = findViewById<TextView>(R.id.details_country_continent_textview)
        val populationTextView = findViewById<TextView>(R.id.details_country_population_textview)
        val flagImageview = findViewById<ImageView>(R.id.details_country_flag_imageview)
        val map = findViewById<MapView>(R.id.details_country_map)
        map.setTileSource(TileSourceFactory.MAPNIK)

        intent.extras?.apply {
            val country = this.getParcelable(COUNTRY_EXTRA, Country::class.java)
            country?.apply {
                countryTextView.text = this.name
                capitalTextView.text = "Capital: $capital"
                continentTextView.text = "Continent: $continent"
                populationTextView.text = "Population: $population"

                Glide.with(this@DetailsCountryActivity)
                    .load(flag)
                    .placeholder(R.drawable.baseline_autorenew_24)
                    .error(R.drawable.baseline_block_24)
                    .into(flagImageview)

                val zoom = 5.0
                Log.d(TAG, zoom.toString())
                map.controller.setZoom(zoom)
                val startPoint = GeoPoint(lat, lng)
                map.controller.setCenter(startPoint)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setButtonToAddInFavorite() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "favorite-countries-db"
        ).build()

        val favoriteCountryButton =
            findViewById<ImageButton>(R.id.details_country_favorite_imagebutton)

        TODO("click button to save in favorite")

        lifecycleScope.launch(Dispatchers.IO) {
            val country = intent.extras?.getParcelable(COUNTRY_EXTRA, Country::class.java)
            if (country != null) {
                lifecycleScope.launch(Dispatchers.IO) {
                    db.countryDao().insertAll(country)
                }
            }
            Toast.makeText(
                this@DetailsCountryActivity,
                "This country has been add to your favorite", Toast.LENGTH_SHORT
            ).show()
        }
    }
}