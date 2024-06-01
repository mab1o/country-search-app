package fr.epf.min1.countrysearch.localstorage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.epf.min1.countrysearch.data.Country

@Dao
interface CountryDao {
    @Query("SELECT * FROM country")
    fun getAll(): List<Country>

    @Insert
    fun insertAll(vararg country: Country)

    @Delete
    fun delete(country: Country)
}