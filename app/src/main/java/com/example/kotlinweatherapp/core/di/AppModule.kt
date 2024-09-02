package com.example.kotlinweatherapp.core.di

import android.content.Context
import androidx.room.Room
import com.example.kotlinweatherapp.core.MainApp
import com.example.kotlinweatherapp.core.db.WeatherDAO
import com.example.kotlinweatherapp.core.db.WeatherDB
import com.example.kotlinweatherapp.core.db.WeatherDBBuilder
import com.example.kotlinweatherapp.features.weather.data.datasources.local.WeatherLocalDataSource
import com.example.kotlinweatherapp.features.weather.data.datasources.local.WeatherLocalDataSourceImpl
import com.example.kotlinweatherapp.features.weather.data.datasources.remote.WeatherRemoteDataSource
import com.example.kotlinweatherapp.features.weather.data.datasources.remote.WeatherRemoteDataSourceImpl
import com.example.kotlinweatherapp.features.weather.data.repos.WeatherRepoImpl
import com.example.kotlinweatherapp.features.weather.domain.repos.WeatherRepo
import com.example.kotlinweatherapp.features.weather.domain.usecases.DeleteWeatherItemUsecase
import com.example.kotlinweatherapp.features.weather.domain.usecases.GetAllWeatherItemsUsecase
import com.example.kotlinweatherapp.features.weather.domain.usecases.GetWeatherItemUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideWeatherRemoteDataSource(): WeatherRemoteDataSource {
        return WeatherRemoteDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideWeatherLocalDataSource(weatherDao:WeatherDAO): WeatherLocalDataSource {
        return WeatherLocalDataSourceImpl(weatherDao)
    }

    @Provides
    @Singleton
    fun provideWeatherRepo(
        weatherRemoteDataSource: WeatherRemoteDataSource,
        weatherLocalDataSource: WeatherLocalDataSource
    ): WeatherRepo {
        return WeatherRepoImpl(
            weatherRemoteDataSource,
            weatherLocalDataSource
        );
    }

    @Provides
    @Singleton
    fun provideGetWeatherItemUsecase(weatherRepo: WeatherRepo): GetWeatherItemUsecase {
        return GetWeatherItemUsecase(weatherRepo)
    }

    @Provides
    @Singleton
    fun provideDeleteWeatherItemUsecase(weatherRepo: WeatherRepo): DeleteWeatherItemUsecase {
        return DeleteWeatherItemUsecase(weatherRepo)
    }

    @Provides
    @Singleton
    fun provideGetAllWeatherItemUsecase(weatherRepo: WeatherRepo): GetAllWeatherItemsUsecase {
        return GetAllWeatherItemsUsecase(weatherRepo)
    }

    @Provides
    @Singleton
    fun ProvideDao(
        weatherDB:WeatherDB
    ) : WeatherDAO {
        return weatherDB.getDao()
    }


    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context) : WeatherDB {
        return WeatherDBBuilder(context).getInstance()
    }

}