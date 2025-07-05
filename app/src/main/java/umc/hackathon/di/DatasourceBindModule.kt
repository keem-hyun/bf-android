package umc.hackathon.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import umc.hackathon.data.datasource.DefaultRegionDataSource
import umc.hackathon.data.datasource.DefaultTestDataSource
import umc.hackathon.data.datasource.JobPostingDataSource
import umc.hackathon.data.datasource.MockJobPostingDataSource
import umc.hackathon.data.datasource.RegionDataSource
import umc.hackathon.data.datasource.TestDataSource
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

    @Binds
    @Singleton
    internal abstract fun bindJobPostingDataSource(
        jobPostingDataSourceImpl: MockJobPostingDataSource
    ): JobPostingDataSource
}