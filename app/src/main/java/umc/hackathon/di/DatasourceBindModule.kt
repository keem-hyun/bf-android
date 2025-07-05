package umc.hackathon.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import umc.hackathon.data.datasource.DefaultRegionDataSource
import umc.hackathon.data.datasource.DefaultTestDataSource
import umc.hackathon.data.datasource.RegionDataSource
import umc.hackathon.data.datasource.TestDataSource
import umc.hackathon.data.repository.DefaultTestRepository
import umc.hackathon.domain.TestRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceBindModule {
    @Binds
    @Singleton
    internal abstract fun bindTestDataSource(
        testDataSourceImpl: DefaultTestDataSource
    ): TestDataSource

    @Binds
    @Singleton
    internal abstract fun bindRegionDataSource(
        regionDataSourceImpl: DefaultRegionDataSource
    ): RegionDataSource
}