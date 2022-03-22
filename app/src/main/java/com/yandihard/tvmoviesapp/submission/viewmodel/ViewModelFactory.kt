package com.yandihard.tvmoviesapp.submission.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yandihard.tvmoviesapp.submission.di.Injection
import com.yandihard.tvmoviesapp.submission.source.CatalogRepository
import com.yandihard.tvmoviesapp.submission.source.FavoriteRepository

class ViewModelFactory private constructor(private val mCatalogRepository: CatalogRepository, private val mFavoriteRepository: FavoriteRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository(context), Injection.provideRepository2nd(context))
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(TvshowViewModel::class.java) -> {
                TvshowViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mFavoriteRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}