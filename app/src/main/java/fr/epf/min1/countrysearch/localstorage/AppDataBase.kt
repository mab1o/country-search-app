package fr.epf.min1.countrysearch.localstorage

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.epf.min1.countrysearch.data.Country

@Database(entities = [Country::class], version=1, exportSchema = false)
abstract class AppDataBase : RoomDatabase(){
    abstract fun countryDao(): CountryDao
}