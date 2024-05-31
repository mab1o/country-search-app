package fr.epf.min1.countrysearch

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
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
        Configuration
            .getInstance()
            .load(applicationContext, getSharedPreferences("osmdroid", MODE_PRIVATE));

        setContentView(R.layout.activity_details_country)
        setCountryInfoAndFlag()
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
                Log.d(TAG,zoom.toString())
                map.controller.setZoom(zoom)
                val startPoint = GeoPoint(lat, lng)
                map.controller.setCenter(startPoint)
            }
        }
    }
}
