package fr.epf.min1.countrysearch

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide

class DetailsCountryActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_country)

        val countryTextView = findViewById<TextView>(R.id.details_country_country_textview)
        val capitalTextView = findViewById<TextView>(R.id.details_country_capital_textview)
        val continentTextView = findViewById<TextView>(R.id.details_country_continent_textview)
        val populationTextView = findViewById<TextView>(R.id.details_country_population_textview)
        val flagImageview = findViewById<ImageView>(R.id.details_country_flag_imageview)

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
            }
        }
    }
}
