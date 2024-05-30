package fr.epf.min1.countrysearch

import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL

private const val TAG = "CountryViewHolder"

class CountryViewHolder(item: View) : RecyclerView.ViewHolder(item)
class ListCountryAdapter(val countries : List<Country>) : RecyclerView.Adapter<CountryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_country, parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        val view = holder.itemView

        val countryTextView = view.findViewById<TextView>(R.id.list_country_country_textview)
        val capitalTextView = view.findViewById<TextView>(R.id.list_country_capital_textview)
        val continentTextView = view.findViewById<TextView>(R.id.list_country_continent_textview)
        val populationTextView = view.findViewById<TextView>(R.id.list_country_population_textview)
        val flagImageview = view.findViewById<ImageView>(R.id.country_list_flag_imageview)

        Log.d(TAG, country.toString())

        countryTextView.text = country.name
        capitalTextView.text = "Capital: ${country.capital}"
        continentTextView.text = "Continent: ${country.continent}"
        populationTextView.text = "Population: ${country.population}"

        // add the flag image
        Glide.with(view.context)
            .load(country.flag)
            .placeholder(R.drawable.baseline_autorenew_24)
            .error(R.drawable.baseline_block_24)
            .into(flagImageview)
    }
}
