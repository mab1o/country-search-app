package fr.epf.min1.countrysearch.activity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.epf.min1.countrysearch.data.Country
import fr.epf.min1.countrysearch.R
import fr.epf.min1.countrysearch.data.click

private const val TAG = "CountryViewHolder"
const val COUNTRY_EXTRA = "details_country"

class CountryViewHolder(item: View) : RecyclerView.ViewHolder(item)
class ListCountryAdapter(val countries: List<Country>) : RecyclerView.Adapter<CountryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        val view = holder.itemView
        fillInInfo(view, country)
        actionOnClick(view, country)
    }

    private fun fillInInfo(view: View, country: Country) {
        val countryTextView = view.findViewById<TextView>(R.id.list_country_country_textview)
        val flagImageview = view.findViewById<ImageView>(R.id.list_country_flag_imageview)

        countryTextView.text = country.name

        // add the flag image
        Glide.with(view.context)
            .load(country.flag)
            .placeholder(R.drawable.baseline_autorenew_24)
            .error(R.drawable.baseline_block_24)
            .into(flagImageview)
    }

    private fun actionOnClick(view: View, country: Country) {
        val card = view.findViewById<CardView>(R.id.list_country_cardview)
        card.click {
            with(it.context) {
                val intent = Intent(this, DetailsCountryActivity::class.java)
                intent.putExtra(fr.epf.min1.countrysearch.activity.COUNTRY_EXTRA, country)
                startActivity(intent)
            }
        }
    }
}
