package com.dicoding.moviecatalogue.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"
    const val RANDOM = "Random"
    
    fun getSortedQuery(filter: String, source: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM")
        if (source == "Movies") {
            simpleQuery.append(" movieentities ")
        }
        else {
            simpleQuery.append(" tvshowentities ")
        }

        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY id DESC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY id ASC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}