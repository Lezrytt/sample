package com.dicoding.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface MovieDao {

//    @Query("select * from movieentities")
    @RawQuery(observedEntities = [MovieEntity::class])
    fun getMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>/*LiveData<List<MovieEntity>>*/

//    @Query("select * from tvshowentities")
    @RawQuery(observedEntities = [TvShowEntity::class])
    fun getTvShow(query: SupportSQLiteQuery): DataSource.Factory<Int, TvShowEntity>/*LiveData<List<TvShowEntity>>*/

    @Query("select * from movieentities where isFavorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("select * from tvshowentities where isFavorite = 1")
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieList: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvList: List<TvShowEntity>)

    @Query("select * from movieentities WHERE id = :movieId")
    fun getDetailMovie(movieId: Int): LiveData<MovieEntity>

    @Query("select * from tvshowentities WHERE id = :tvShowId")
    fun getDetailTvShow(tvShowId: Int): LiveData<TvShowEntity>

    @Update
    fun updateDetailMovie(movie: MovieEntity)

    @Update
    fun updateDetailTvShow(tvShow: TvShowEntity)

    @Query("select * from movieentities")
    fun getAllMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("select * from tvshowentities")
    fun getAllTvShow(): DataSource.Factory<Int, TvShowEntity>

}