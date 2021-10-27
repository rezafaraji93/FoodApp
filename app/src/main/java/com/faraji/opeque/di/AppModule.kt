package com.faraji.opeque.di

import com.faraji.opeque.core.data.remote.MenuApi
import com.faraji.opeque.core.data.repository.MenuRepository
import com.faraji.opeque.core.data.repository.MenuRepositoryImpl
import com.faraji.opeque.core.domain.use_cases.GetMenuUseCase
import com.faraji.opeque.core.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): MenuApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MenuApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMenuRepository(api: MenuApi): MenuRepository {
        return MenuRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: MenuRepository): GetMenuUseCase {
        return GetMenuUseCase(repository)
    }
}