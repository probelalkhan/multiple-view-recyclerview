package net.simplifiedcoding.multiviewlist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.simplifiedcoding.multiviewlist.data.network.Api
import net.simplifiedcoding.multiviewlist.data.network.RemoteDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTargetApi(
        remoteDataSource: RemoteDataSource
    ): Api {
        return remoteDataSource.buildApi(Api::class.java)
    }
}