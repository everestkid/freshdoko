package com.teamonetech.freshdoko.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.teamonetech.freshdoko.BuildConfig
import com.teamonetech.freshdoko.domain.models.ProductsModel


@Database(entities = [ProductsModel::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDb? = null

        fun getDatabase(context: Context): AppDb {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    BuildConfig.APPLICATION_ID
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}