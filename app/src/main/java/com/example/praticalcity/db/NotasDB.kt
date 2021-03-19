package com.example.praticalcity.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.praticalcity.dao.NotasDao
import com.example.praticalcity.entities.notasEntities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(notasEntities::class), version = 5, exportSchema = false)
public abstract class NotasDB : RoomDatabase() {

    abstract fun notasDao(): NotasDao

    private class WordDataBaseCallback(
        private val scope: CoroutineScope
        ): RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {
                    database -> scope.launch {
                        var notasDao = database.notasDao()

                        //nota de teste
                        var nota = notasEntities(1, "titulo1", "pequena observação")
                        notasDao.insert(nota)
                    }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NotasDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NotasDB {

            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotasDB::class.java,
                    "notas_database"
                )
                        //.fallbackToDestructiveMigration()
                        .addCallback(WordDataBaseCallback(scope))
                        .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
